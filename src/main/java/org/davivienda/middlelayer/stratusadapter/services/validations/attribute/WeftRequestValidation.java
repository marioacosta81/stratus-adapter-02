package org.davivienda.middlelayer.stratusadapter.services.validations.attribute;

import org.davivienda.middlelayer.stratusadapter.model.exceptions.RequestValidationException;

public abstract class WeftRequestValidation<T> implements  IWeftRequestValidation<T>{

    protected T config;
    protected String value;

    public WeftRequestValidation(T config, String value){
        this.config = config;
        this.value = value;
    }

    @Override
    public abstract void validate() throws RequestValidationException;

}
