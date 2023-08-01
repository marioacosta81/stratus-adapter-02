package org.davivienda.middlelayer.stratusadapter.application.services.validations;

import org.davivienda.middlelayer.stratusadapter.application.model.exceptions.RequestValidationException;

public abstract class RuleRequestValidation<T> implements IStratusAdapterValidation<T> {

    protected T config;
    protected String value;
    @Override
    public abstract void validate() throws RequestValidationException;

}
