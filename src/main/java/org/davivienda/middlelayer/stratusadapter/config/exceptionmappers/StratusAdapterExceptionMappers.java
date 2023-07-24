package org.davivienda.middlelayer.stratusadapter.config.exceptionmappers;

import jakarta.ws.rs.core.Response;
import org.davivienda.middlelayer.stratusadapter.model.dtos.StratusErrorResponseDto;
import org.davivienda.middlelayer.stratusadapter.model.exceptions.RequestValidationException;
import org.davivienda.middlelayer.stratusadapter.model.exceptions.StratusAdapterException;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

public class StratusAdapterExceptionMappers {



    @ServerExceptionMapper
    public RestResponse<StratusErrorResponseDto> mapException(RuntimeException e){
        StratusErrorResponseDto errorResponse = new StratusErrorResponseDto(e.getMessage());
        return RestResponse.status(Response.Status.INTERNAL_SERVER_ERROR,errorResponse);
    }

    @ServerExceptionMapper
    public RestResponse<StratusErrorResponseDto> mapException(StratusAdapterException e){
        StratusErrorResponseDto errorResponse = new StratusErrorResponseDto(e.getMessage());
        return RestResponse.status(e.getStatus(),errorResponse);
    }


    @ServerExceptionMapper
    public RestResponse<StratusErrorResponseDto> mapException(RequestValidationException e){
        StratusErrorResponseDto errorResponse = new StratusErrorResponseDto(e.getMessage());
        return RestResponse.status(e.getStatus(),errorResponse);
    }

}
