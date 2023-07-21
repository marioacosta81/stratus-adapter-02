package org.davivienda.middlelayer.stratusadapter.services.validations.attribute;

import org.davivienda.middlelayer.stratusadapter.model.dtos.DataAttributeDto;
import org.davivienda.middlelayer.stratusadapter.model.dtos.WeftRequestDto;
import org.davivienda.middlelayer.stratusadapter.model.exceptions.RequestValidationException;

public interface IWeftRequestValidation<T>{

    void validate()throws RequestValidationException;

}
