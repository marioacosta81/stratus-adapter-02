package org.davivienda.middlelayer.stratusadapter.model.exceptions;

import jakarta.ws.rs.core.Response;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class StratusAdapterException extends RuntimeException {

    private Response.Status status;


    public StratusAdapterException(Response.Status status, String message) {
        super(message);
        this.status = status;
    }



}
