package org.davivienda.middlelayer.stratusadapter.utilities;

public class StringUtilities {

    public static final String EMPTY = "";

    public static String fixZeroLeftStringSize(String stringValue, Integer size){
        if(null == stringValue || stringValue.isEmpty()){
            return stringValue;
        }
        if(size < stringValue.length()){
            return stringValue.substring(0,size);
        }
        while ( size > stringValue.length()){
            stringValue = "0" + stringValue;
        }
        return stringValue;
    }



}
