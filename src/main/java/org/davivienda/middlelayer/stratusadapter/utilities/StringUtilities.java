package org.davivienda.middlelayer.stratusadapter.utilities;

import org.davivienda.middlelayer.stratusadapter.model.dtos.DataAttributeDto;

public class StringUtilities {

    public static final String EMPTY = "";

    public static String fixPaddingStringSize(DataAttributeDto dataAttributeDto, String stringValue){
        var size = dataAttributeDto.getSizeAttribute();
        var paddingCharacter = dataAttributeDto.getPaddingCharacter();
        var leftPaddingDirection = dataAttributeDto.getLeftPaddingDirection();

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
