package org.davivienda.middlelayer.stratusadapter.services.validations;

import org.davivienda.middlelayer.stratusadapter.model.exceptions.RequestValidationException;

public interface IRuleRequestValidation<T>{

    void validate()throws RequestValidationException;

}
