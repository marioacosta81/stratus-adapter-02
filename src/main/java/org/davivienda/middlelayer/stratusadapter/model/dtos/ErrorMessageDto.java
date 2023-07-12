package org.davivienda.middlelayer.stratusadapter.model.dtos;

import lombok.Data;

@Data
public class ErrorMessageDto {
    private String message;
    private Boolean status;

    public ErrorMessageDto(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }


}
