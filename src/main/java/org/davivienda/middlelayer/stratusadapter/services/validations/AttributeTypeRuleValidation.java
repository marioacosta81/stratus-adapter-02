package org.davivienda.middlelayer.stratusadapter.services.validations;

import org.davivienda.middlelayer.stratusadapter.model.dtos.DataAttributeDto;
import org.davivienda.middlelayer.stratusadapter.model.enums.ConfigDataTypeEnum;
import org.davivienda.middlelayer.stratusadapter.model.exceptions.RequestValidationException;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AttributeTypeRuleValidation<T> extends RuleRequestValidation<T> {


    @ConfigProperty(name = "message.error.attribute.type.validation")
    private String errorMessage;

    @ConfigProperty(name = "date.pattern.attribute.type.validation")
    private String datePattern;

    public AttributeTypeRuleValidation(T config, String value) {
        super(config, value);
    }

    @Override
    public void validate() throws RequestValidationException {

        DataAttributeDto attribute = (DataAttributeDto)this.config;

        ConfigDataTypeEnum type =   (ConfigDataTypeEnum.valueOfLabel(attribute.getDataConfigType())).get();

        if(ConfigDataTypeEnum.NUMBER.equals( type )){
            try {
                Double.parseDouble(value);
            }catch (NumberFormatException  e){
                throw new RequestValidationException( String.format( errorMessage,attribute.getIdName(),value,type.getLabel()));
            }

        }else  if(ConfigDataTypeEnum.DATE.equals( type )){

            final var dateFormat = new SimpleDateFormat(datePattern);
            dateFormat.setLenient(false);
            try
            {
                dateFormat.parse(value);
            }catch (ParseException e){
                throw new RequestValidationException( String.format( errorMessage,attribute.getIdName(),value,type.getLabel()));
            }
        }
    }
}
