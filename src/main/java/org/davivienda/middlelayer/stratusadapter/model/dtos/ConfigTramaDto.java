package org.davivienda.middlelayer.stratusadapter.model.dtos;


import lombok.Data;

import java.util.List;

@Data
public class ConfigTramaDto {

    private int size;
    private List<DataAttributeDto> attributesList;

}
