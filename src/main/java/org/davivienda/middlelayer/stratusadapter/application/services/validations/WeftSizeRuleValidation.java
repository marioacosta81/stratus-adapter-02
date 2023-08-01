package org.davivienda.middlelayer.stratusadapter.application.services.validations;

import jakarta.enterprise.context.Dependent;
import org.davivienda.middlelayer.stratusadapter.application.model.dtos.ConfigTramaDto;
import org.davivienda.middlelayer.stratusadapter.application.model.exceptions.RequestValidationException;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Dependent
public class WeftSizeRuleValidation<T> extends RuleRequestValidation<T> {

    @ConfigProperty(name = "date.pattern.attribute.type.validation")
    private String datePattern;

    @ConfigProperty(name = "message.error.attribute.type.validation")
    private String errorMessageDatePattern;

    @ConfigProperty(name = "message.error.data.type.not.found")
    private String errorMessageTypeNotFound;

    public void create(T config, String value) {
        super.config = config;
        super.value = value;
    }

    @Override
    public void validate() throws RequestValidationException {
        ConfigTramaDto config = (ConfigTramaDto)this.config;
    }
}
