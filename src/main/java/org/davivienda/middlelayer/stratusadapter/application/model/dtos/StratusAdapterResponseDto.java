package org.davivienda.middlelayer.stratusadapter.application.model.dtos;

import lombok.Data;

@Data
public class StratusAdapterResponseDto {

    private String message;
    private Boolean status;

    private Object dataResponse;
}