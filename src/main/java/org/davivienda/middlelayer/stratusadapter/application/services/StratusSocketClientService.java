package org.davivienda.middlelayer.stratusadapter.application.services;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import org.davivienda.middlelayer.stratusadapter.application.model.dtos.DataAttributeDto;
import org.davivienda.middlelayer.stratusadapter.application.model.exceptions.StratusAdapterException;
import org.davivienda.middlelayer.stratusadapter.application.services.validations.AttributeTypeRuleValidation;
import org.davivienda.middlelayer.stratusadapter.application.utilities.SocketUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
