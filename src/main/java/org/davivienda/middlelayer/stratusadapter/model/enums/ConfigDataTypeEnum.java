package org.davivienda.middlelayer.stratusadapter.model.enums;

import java.util.Arrays;
import java.util.Optional;

public enum ConfigDataTypeEnum {
    STRING("string"),
    NUMBER("number"),
    DATE("date");


    private String label;

    ConfigDataTypeEnum(String label){
        this.label = label;
    }

    public static Optional<ConfigDataTypeEnum> valueOfLabel(String label){
        return Arrays.stream(values()).filter( e -> e.label.equals(label)).findFirst();
    }

    public String getLabel(){
        return this.label;
    }


}
