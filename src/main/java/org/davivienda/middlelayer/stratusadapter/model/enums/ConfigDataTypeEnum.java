package org.davivienda.middlelayer.stratusadapter.model.enums;

import javax.lang.model.element.Element;

public enum ConfigDataTypeEnum {
    STRING("string"),
    NUMBER("number"),
    DATE("date");


    private String label;

    ConfigDataTypeEnum(String label){
        this.label = label;
    }

    public static ConfigDataTypeEnum valueOfLabel(String label){
        for(ConfigDataTypeEnum e: values()){
            if(e.label.equals(label)){
                return e;
            }
        }
        return null;
    }


}
