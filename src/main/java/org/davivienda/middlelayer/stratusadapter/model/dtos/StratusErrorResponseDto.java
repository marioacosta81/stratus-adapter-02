package org.davivienda.middlelayer.stratusadapter.model.dtos;

import lombok.Data;

@Data
public class StratusErrorResponseDto {
    private String message;
    private Boolean status;

    public StratusErrorResponseDto(String message) {
        this.message = message;
        this.status = false;
    }


}
