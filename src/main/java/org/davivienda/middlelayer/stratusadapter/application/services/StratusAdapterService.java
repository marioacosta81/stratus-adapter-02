package org.davivienda.middlelayer.stratusadapter.application.services;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import java.util.*;
import java.util.Map.Entry;

import org.davivienda.middlelayer.stratusadapter.application.model.dtos.BuildDataWeftRequestDto;
import org.davivienda.middlelayer.stratusadapter.application.model.dtos.BuildStratusWeftRequestDto;
import org.davivienda.middlelayer.stratusadapter.application.model.dtos.ConfigTramaDto;
import org.davivienda.middlelayer.stratusadapter.application.model.dtos.DataAttributeDto;
import org.davivienda.middlelayer.stratusadapter.application.model.exceptions.StratusAdapterException;
import org.davivienda.middlelayer.stratusadapter.application.services.validations.AttributeTypeRuleValidation;
import org.davivienda.middlelayer.stratusadapter.application.utilities.StringUtilities;
import org.davivienda.middlelayer.stratusadapter.application.utilities.ValidationsUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Dependent
public class StratusAdapterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            StratusAdapterService.class);

    @Inject
    AttributeTypeRuleValidation<DataAttributeDto> attributeTypeRuleValidation;

    @Inject
    StratusSocketClientService stratusSocketClientService;


    public String buildStratusWeft(BuildStratusWeftRequestDto request)
            throws StratusAdapterException {

        return  stratusSocketClientService.getRequestStratus("aaabbb");
/*
        ConfigTramaDto configTramaDto = request.getConfigTramaDto();
        Map<Object, Object> data = (Map<Object, Object>) request.getData();

        List<DataAttributeDto> listConfigAttributes = configTramaDto.getAttributesList();
        Collections.sort(
                listConfigAttributes,
                Comparator.comparing(DataAttributeDto:: getPosition));

        var sbWeft = new StringBuilder();
        listConfigAttributes.forEach(attribute -> {
            var attributeDataValue = StringUtilities.EMPTY;
            Optional<String> opAttributeDataValue = getValueInData(
                    data,
                    attribute.getId_name());
            if (opAttributeDataValue.isEmpty()) {
                attributeDataValue = attribute.getDefault_value();
            } else {
                attributeDataValue = opAttributeDataValue.get();
            }
            attributeValidations(attribute, attributeDataValue);
            attributeDataValue = StringUtilities.fixPaddingStringSize(attribute, attributeDataValue);
            sbWeft.append(attributeDataValue);
        });
        return sbWeft.toString();*/
    }

    private Optional<String> getValueInData(
            Map<Object, Object> data,
            String idNameAttribute) throws StratusAdapterException {
        String[] idNameLevels = idNameAttribute.split(StringUtilities.DOC);

        var nameNextLevel = idNameAttribute;
        if(idNameLevels.length>1){
            nameNextLevel = nameNextLevel.replace(idNameLevels[0].concat("."),StringUtilities.EMPTY);
        }

        //var valueInData = StringUtilities.EMPTY;
        for(int i=0;i<idNameLevels.length;i++) {

            var name = idNameLevels[i];
            Optional<Map.Entry<Object, Object>> attributeFilter = data
                    .entrySet()
                    .stream()
                    .filter(entry -> existEntry(name, entry))
                    .findFirst();

            if (attributeFilter.isEmpty()) {
                return Optional.empty();
            }

            return getAttributeStringValue(attributeFilter, nameNextLevel);

        }
        return Optional.empty();
    }

    private Optional<String> getAttributeStringValue(Optional<Entry<Object, Object>> attributeFilter, String nameNextLevel) {
        String valueInData;
        if(!(attributeFilter.get().getValue()  instanceof Map)){
            valueInData = String.valueOf(attributeFilter.get().getValue());
            return Optional.ofNullable(valueInData);
        }else{
            return getValueInData((Map<Object, Object>) attributeFilter.get().getValue(), nameNextLevel);
        }
    }

    private boolean existEntry(
            String idNameAttribute,
            Entry<Object, Object> entry) {
        if (entry.getKey() instanceof Map) {
            getValueInData((Map<Object, Object>) entry.getKey(), idNameAttribute);
        }
        return entry.getKey().equals(idNameAttribute);
    }

    public Map<Object, Object> buildDataWeft(BuildDataWeftRequestDto request)
            throws StratusAdapterException {
        ConfigTramaDto configTramaDto = request.getConfigTramaDto();

        List<DataAttributeDto> listConfigAttributes = configTramaDto.getAttributesList();
        Collections.sort(
                listConfigAttributes,
                Comparator.comparing(DataAttributeDto::getPosition));

        Map<Object, Object> data = getMapFromDataWeft(request, listConfigAttributes);

        return data;
    }

    private Map<Object, Object> getMapFromDataWeft(
            BuildDataWeftRequestDto request,
            List<DataAttributeDto> listConfigAttributes) {
        final String weft = request.getWeft();
        Map<Object, Object> data = new HashMap<>();


        for(DataAttributeDto attribute: listConfigAttributes) {
            Integer weftPosition = attribute.getPosition();
            Integer size = attribute.getLength();

            var attributeValue = getAttributeValueFromDataWeft(attribute, weft, weftPosition, size);

            attributeValidations(attribute, attributeValue);


            Map<Object, Object> item = buildMapFromAttribute(attribute, attributeValue);


            mergeMaps(data, item);


        }
        return data;
    }

    private Map<Object, Object> buildMapFromAttribute(DataAttributeDto attribute, String attributeValue) {
        String[] nameLevels = attribute.getId_name().split(  StringUtilities.DOC );
        Map<Object,Object> item = new HashMap<Object,Object>();
        for(int i=nameLevels.length-1;i>=0;i--){
            String level = nameLevels[i];
            Map<Object,Object> temp = new HashMap<>();
            if(item.isEmpty()) {
                temp.put(level, attributeValue);
            }else{
                temp.put(level, item);
            }
            item = temp;

        }
        return item;
    }

    private String getAttributeValueFromDataWeft(DataAttributeDto attribute, String weft, Integer weftPosition, Integer size) {
        var attributeValue = weft.substring(
                weftPosition - 1,
                (weftPosition + size) - 1);

        attributeValue = attributeValue.replace(
                attribute.getPadding_character(),
                StringUtilities.EMPTY);
        return attributeValue;
    }

    private boolean mergeMaps(Map<Object, Object> data, Map<Object, Object> item) {
        for (Entry<Object,Object> entryItem : item.entrySet()) {

            if(data.isEmpty() ){
                data.putAll(item);
                return true;
            }
            if(!data.containsKey(  entryItem.getKey() )){
                data.putAll(item);
            }

            for (Entry<Object,Object> entryData: data.entrySet()) {
                if(entryData.getKey().equals(entryItem.getKey()   )){
                    if(entryData.getValue() instanceof  Map  &&  entryItem.getValue() instanceof  Map){
                        return mergeMaps((Map<Object, Object>) entryData.getValue(), (Map<Object, Object>) entryItem.getValue());
                    }
                    data.putAll(item);
                    return true;
                }
            }
        }
        return false;
    }


    private void attributeValidations(
            DataAttributeDto attribute,
            String attributeValue) {
        attributeTypeRuleValidation.create(attribute, attributeValue);
        ValidationsUtilities.WeftRequestValidationsExecute(
                attributeTypeRuleValidation);
    }
}
