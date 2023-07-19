package org.davivienda.middlelayer.stratusadapter.config.exceptionmappers;

import jakarta.ws.rs.core.Response;
import org.davivienda.middlelayer.stratusadapter.model.exceptions.StratusAdapterException;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

public class StratusAdapterExceptionMappers {

    @ServerExceptionMapper
    public RestResponse<String> mapException(StratusAdapterException e){
        return RestResponse.status( Response.Status.BAD_REQUEST, "ejemplo de excepcion" + e.getMessage()  );
    }

    @ServerExceptionMapper
    public RestResponse<String> mapException(RuntimeException e){
        return RestResponse.status( Response.Status.INTERNAL_SERVER_ERROR, "ejemplo de RuntimeException " + e.getMessage()  );
    }

}
