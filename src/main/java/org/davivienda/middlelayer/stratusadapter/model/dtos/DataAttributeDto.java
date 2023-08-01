package org.davivienda.middlelayer.stratusadapter.model.dtos;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class DataAttributeDto {

    private String id_name;
    private Integer position;
    private Integer length;
    private String data_type;
    private String padding_character;
    private Boolean left_padding;
    private String default_value;

}
