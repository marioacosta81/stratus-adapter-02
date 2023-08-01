package org.davivienda.middlelayer.stratusadapter.application.model.dtos;


import lombok.Data;

import java.util.List;

@Data
public class ConfigTramaDto {

    private String task_code;
    private String transaction_code;
    private String total_length;
    private List<DataAttributeDto> attributesList;

}
