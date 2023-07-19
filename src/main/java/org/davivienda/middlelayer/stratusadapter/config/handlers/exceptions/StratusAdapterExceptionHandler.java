package org.davivienda.middlelayer.stratusadapter.config.handlers.exceptions;

import org.apache.http.HttpStatus;
import org.davivienda.middlelayer.stratusadapter.model.exceptions.StratusAdapterException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class StratusAdapterExceptionHandler  implements ExceptionMapper<StratusAdapterException> {
    @Override
    public Response toResponse(StratusAdapterException e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),"aaaaaa" ).build();
    }
}
