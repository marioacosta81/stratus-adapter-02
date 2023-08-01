package org.davivienda.middlelayer.stratusadapter.application.utilities;

import org.davivienda.middlelayer.stratusadapter.application.model.dtos.DataAttributeDto;

public class StringUtilities {

    public static final String EMPTY = "";
    public static final String DOC = ".";

    public static String fixPaddingStringSize(DataAttributeDto dataAttributeDto, String stringValue){
        var size = dataAttributeDto.getLength();
        var paddingCharacter = dataAttributeDto.getPadding_character();
        var leftPaddingDirection = dataAttributeDto.getLeft_padding();

        if(null == stringValue || stringValue.isEmpty()){
            return stringValue;
        }
        if(size < stringValue.length()){
            return stringValue.substring(0,size);
        }
        while ( size > stringValue.length()){
            if(leftPaddingDirection) {
                stringValue = paddingCharacter + stringValue;
            }else{
                stringValue = stringValue + paddingCharacter;
            }
        }
        return stringValue;
    }



}