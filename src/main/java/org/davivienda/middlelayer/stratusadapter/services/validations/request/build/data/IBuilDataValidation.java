package org.davivienda.middlelayer.stratusadapter.services.validations.request.build.data;

import org.davivienda.middlelayer.stratusadapter.model.dtos.DataAttributeDto;
import org.davivienda.middlelayer.stratusadapter.model.exceptions.RequestValidationException;

public interface IBuilDataValidation {
    void validate(DataAttributeDto attribute, String attributeValue)throws RequestValidationException;
}
