package org.davivienda.middlelayer.stratusadapter.model.dtos;
import lombok.Data;

@Data
public class DataAttributeDto {

    private String idName;
    private Integer weftPosition;
    private Integer sizeAttribute;
    private String dataConfigType;
    private Character paddingCharacter;
    private Boolean leftPaddingDirection;
    private String defaultValue;

}
