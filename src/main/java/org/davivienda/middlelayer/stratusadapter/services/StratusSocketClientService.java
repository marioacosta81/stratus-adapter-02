package org.davivienda.middlelayer.stratusadapter.services;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import org.davivienda.middlelayer.stratusadapter.model.dtos.BuildDataWeftRequestDto;
import org.davivienda.middlelayer.stratusadapter.model.dtos.BuildStratusWeftRequestDto;
import org.davivienda.middlelayer.stratusadapter.model.dtos.ConfigTramaDto;
import org.davivienda.middlelayer.stratusadapter.model.dtos.DataAttributeDto;
import org.davivienda.middlelayer.stratusadapter.model.exceptions.StratusAdapterException;
import org.davivienda.middlelayer.stratusadapter.services.validations.AttributeTypeRuleValidation;
import org.davivienda.middlelayer.stratusadapter.utilities.SocketUtilities;
import org.davivienda.middlelayer.stratusadapter.utilities.StringUtilities;
import org.davivienda.middlelayer.stratusadapter.utilities.ValidationsUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.Map.Entry;

@Dependent
public class StratusSocketClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            StratusSocketClientService.class);

    @Inject
    AttributeTypeRuleValidation<DataAttributeDto> attributeTypeRuleValidation;

    public String getRequestStratus(String request)
            throws StratusAdapterException {
        return SocketUtilities.requestSocket("127.0.0.1",9999,request   );
    }
}
