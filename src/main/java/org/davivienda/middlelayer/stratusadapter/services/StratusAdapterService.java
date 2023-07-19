package org.davivienda.middlelayer.stratusadapter.services;

import jakarta.enterprise.context.Dependent;
import org.davivienda.middlelayer.stratusadapter.model.dtos.BuildDataWeftRequestDto;
import org.davivienda.middlelayer.stratusadapter.model.dtos.ConfigTramaDto;
import org.davivienda.middlelayer.stratusadapter.model.dtos.DataAttributeDto;
import org.davivienda.middlelayer.stratusadapter.model.dtos.BuildStratusWeftRequestDto;
import org.davivienda.middlelayer.stratusadapter.model.exceptions.StratusAdapterException;
import org.davivienda.middlelayer.stratusadapter.utilities.StringUtilities;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Dependent
public class StratusAdapterService {

    private static final Logger LOGGER = LoggerFactory.getLogger( StratusAdapterService.class);

    public String buildStratusWeft(BuildStratusWeftRequestDto request  )throws StratusAdapterException {

        ConfigTramaDto configTramaDto = request.getConfigTramaDto();
        Map<Object,Object> data = (Map<Object,Object> )request.getData();

        List<DataAttributeDto> listConfigAttributes = configTramaDto.getAttributesList();
        Collections.sort(listConfigAttributes,Comparator.comparing(DataAttributeDto::getWeftPosition) );

        StringBuilder sbWeft = new StringBuilder();
        for(DataAttributeDto attribute: listConfigAttributes){
            String attributeData = getValueInData( data,attribute.getIdName());
            attributeData = StringUtilities.fixZeroLeftStringSize(attributeData, attribute.getSizeAttribute());
            sbWeft.append(attributeData);
        }
        return sbWeft.toString();
    }


    private String getValueInData(Map<Object,Object> data, String idNameAttribute)throws StratusAdapterException{
        Optional<Map.Entry<Object,Object>> attributeFilter =  data.entrySet().stream().filter(entry ->{
            if( entry.getKey() instanceof Map){
                getValueInData( (Map<Object,Object>)entry.getKey(),idNameAttribute);
            }
            return entry.getKey().equals( idNameAttribute );

        }).findFirst();

        if(attributeFilter.isEmpty()){
            LOGGER.error("El campo  " + idNameAttribute +" no existe en la data"  );
            throw new StratusAdapterException("El campo  " + idNameAttribute +" no existe en la data"  ) ;
        }

        return  String.valueOf( attributeFilter.get().getValue());

        /*for(Map.Entry entry: data.entrySet()){
            if( entry.getKey() instanceof Map){
                return getValueInData( (Map<String,Object>)entry.getKey(),idNameAttribute);
            }

            if( entry.getKey().equals( idNameAttribute   )    ){
                return String.valueOf(entry.getValue() );
            }
        }*/
    }

    public Map<String,Object> buildDataWeft(BuildDataWeftRequestDto request)throws StratusAdapterException {



        ConfigTramaDto configTramaDto = request.getConfigTramaDto();

        List<DataAttributeDto> listConfigAttributes = configTramaDto.getAttributesList();
        Collections.sort(listConfigAttributes, Comparator.comparing(DataAttributeDto::getWeftPosition));

        final String weft = request.getWeft();
        Map<String, Object> data = new HashMap<>();

        listConfigAttributes.forEach( attribute-> {
            Integer weftPosition = attribute.getWeftPosition();
            Integer size = attribute.getSizeAttribute();
            data.put(attribute.getIdName(), weft.substring(weftPosition - 1, (weftPosition + size) - 1));
        });


        return data;


    }







}
