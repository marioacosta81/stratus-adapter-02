package org.davivienda.middlelayer.stratusadapter.services;

import jakarta.enterprise.context.Dependent;
import jakarta.ws.rs.core.Response;
import org.davivienda.middlelayer.stratusadapter.model.dtos.BuildDataWeftRequestDto;
import org.davivienda.middlelayer.stratusadapter.model.dtos.ConfigTramaDto;
import org.davivienda.middlelayer.stratusadapter.model.dtos.DataAttributeDto;
import org.davivienda.middlelayer.stratusadapter.model.dtos.BuildStratusWeftRequestDto;
import org.davivienda.middlelayer.stratusadapter.model.exceptions.StratusAdapterException;
import org.davivienda.middlelayer.stratusadapter.services.validations.attribute.AttributeTypeValidation;
import org.davivienda.middlelayer.stratusadapter.services.validations.attribute.WeftRequestValidation;
import org.davivienda.middlelayer.stratusadapter.utilities.StringUtilities;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.Map.Entry;

@Dependent
public class StratusAdapterService {

    private static final Logger LOGGER = LoggerFactory.getLogger( StratusAdapterService.class);


    @ConfigProperty(name = "message.error.attribute.no.data")
    private String messageErrorAttributeNoData;


    public String buildStratusWeft(BuildStratusWeftRequestDto request  )throws StratusAdapterException {

        ConfigTramaDto configTramaDto = request.getConfigTramaDto();
        Map<Object,Object> data = (Map<Object,Object> )request.getData();

        List<DataAttributeDto> listConfigAttributes = configTramaDto.getAttributesList();
        Collections.sort(listConfigAttributes,Comparator.comparing(DataAttributeDto::getWeftPosition) );

        var sbWeft = new StringBuilder();
        listConfigAttributes.forEach(attribute -> {
            String attributeData = getValueInData( data,attribute.getIdName());
            attributeData = StringUtilities.fixZeroLeftStringSize(attributeData, attribute.getSizeAttribute());
            sbWeft.append(attributeData);
        });

        return sbWeft.toString();
    }


    private String getValueInData(Map<Object,Object> data, String idNameAttribute)throws StratusAdapterException{
        Optional<Map.Entry<Object,Object>> attributeFilter =  data.entrySet().stream().filter(entry ->extracted(idNameAttribute, entry)).findFirst();

        if(attributeFilter.isEmpty()){
            LOGGER.error( messageErrorAttributeNoData,idNameAttribute );
            throw new StratusAdapterException(
                    Response.Status.BAD_REQUEST,
                    String.format(messageErrorAttributeNoData,idNameAttribute ));
        }
        return  String.valueOf( attributeFilter.get().getValue());
    }


    private boolean extracted(String idNameAttribute, Entry<Object, Object> entry) {
        if( entry.getKey() instanceof Map){
            getValueInData( (Map<Object,Object>)entry.getKey(),idNameAttribute);
        }
        return entry.getKey().equals( idNameAttribute );
    }

    public Map<String,Object> buildDataWeft(BuildDataWeftRequestDto request)throws StratusAdapterException {
        ConfigTramaDto configTramaDto = request.getConfigTramaDto();

        List<DataAttributeDto> listConfigAttributes = configTramaDto.getAttributesList();
        Collections.sort(listConfigAttributes, Comparator.comparing(DataAttributeDto::getWeftPosition));

        final String weft = request.getWeft();
        Map<String, Object> data = new HashMap<>();

        //listConfigAttributes.forEach( attribute-> {
        for (DataAttributeDto attribute : listConfigAttributes){


            Integer weftPosition = attribute.getWeftPosition();
        Integer size = attribute.getSizeAttribute();

        var attributeValue = weft.substring(weftPosition - 1, (weftPosition + size) - 1);


        AttributeTypeValidation<DataAttributeDto> attributeTypeValidation
                = new AttributeTypeValidation<>(attribute, attributeValue);

        weftValidations(new WeftRequestValidation[]{attributeTypeValidation});


        data.put(attribute.getIdName(), attributeValue);
    }
            //});
        return data;
    }git status


    private void weftValidations(WeftRequestValidation[] arrayValidations)throws StratusAdapterException{
        Arrays.asList(arrayValidations).forEach(validation -> {
            validation.validate( );
                });
    }







}
