package org.davivienda.middlelayer.stratusadapter.services.validations;

import org.davivienda.middlelayer.stratusadapter.model.exceptions.RequestValidationException;

public interface IStratusAdapterValidation<T>{

    void validate()throws RequestValidationException;

}
