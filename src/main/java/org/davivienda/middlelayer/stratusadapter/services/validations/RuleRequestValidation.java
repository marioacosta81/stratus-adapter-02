package org.davivienda.middlelayer.stratusadapter.services.validations;

import org.davivienda.middlelayer.stratusadapter.model.exceptions.RequestValidationException;

public abstract class RuleRequestValidation<T> implements IStratusAdapterValidation<T> {

    protected T config;
    protected String value;
    @Override
    public abstract void validate() throws RequestValidationException;

}
