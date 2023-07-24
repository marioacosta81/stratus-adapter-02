package org.davivienda.middlelayer.stratusadapter.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.Consumes;
import org.davivienda.middlelayer.stratusadapter.model.dtos.BuildDataWeftRequestDto;
import org.davivienda.middlelayer.stratusadapter.model.dtos.BuildStratusWeftRequestDto;
import org.davivienda.middlelayer.stratusadapter.model.dtos.StratusAdapterResponseDto;
import org.davivienda.middlelayer.stratusadapter.model.exceptions.StratusAdapterException;
import org.davivienda.middlelayer.stratusadapter.services.StratusAdapterService;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import java.util.Map;

@Path("/adaptersatratus")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StratusAdapterResources {

    @Inject
    StratusAdapterService stratusAdapterService;

    @POST()
    @Path("/build-weft")
    public RestResponse<StratusAdapterResponseDto> buildStratusWeft(  BuildStratusWeftRequestDto request  ) throws StratusAdapterException
    {
        final String strWeft = stratusAdapterService.buildStratusWeft(request);

        StratusAdapterResponseDto response = new StratusAdapterResponseDto();
        response.setDataResponse(strWeft);
        response.setStatus(true);
        return RestResponse.ok(response);
    }



    @POST()
    @Path("/build-data")
    public RestResponse<StratusAdapterResponseDto> buildDataWeft(BuildDataWeftRequestDto request  ) throws StratusAdapterException
    {

        final Map<String,Object> data = stratusAdapterService.buildDataWeft(request);

        StratusAdapterResponseDto response = new StratusAdapterResponseDto();
        response.setDataResponse(data);
        response.setStatus(true);
        return RestResponse.ok(response,MediaType.APPLICATION_JSON);
    }







}
