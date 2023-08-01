package org.davivienda.middlelayer.stratusadapter.application.services.validations;

import org.davivienda.middlelayer.stratusadapter.application.model.exceptions.RequestValidationException;

public interface IStratusAdapterValidation<T>{

    void validate()throws RequestValidationException;

}
