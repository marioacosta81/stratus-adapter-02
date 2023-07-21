package org.davivienda.middlelayer.stratusadapter.model.exceptions;

import jakarta.ws.rs.core.Response;
import lombok.Getter;

@Getter
public class RequestValidationException extends RuntimeException {

    private Response.Status status;


    public RequestValidationException(String message) {
        super(message);
        this.status = Response.Status.BAD_REQUEST;
    }



}
