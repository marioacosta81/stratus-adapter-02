package org.davivienda.middlelayer.stratusadapter.services.validations.request.build.weft;

import org.davivienda.middlelayer.stratusadapter.model.dtos.DataAttributeDto;
import org.davivienda.middlelayer.stratusadapter.model.exceptions.RequestValidationException;

public interface IBuildWeftValidation {
    void validate(DataAttributeDto attribute, String attributeValue)throws RequestValidationException;
}
