package org.davivienda.middlelayer.stratusadapter.application.services.validations;

import jakarta.enterprise.context.Dependent;
import org.davivienda.middlelayer.stratusadapter.application.model.dtos.DataAttributeDto;
import org.davivienda.middlelayer.stratusadapter.application.model.enums.ConfigDataTypeEnum;
import org.davivienda.middlelayer.stratusadapter.application.model.exceptions.RequestValidationException;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Dependent
public class AttributeTypeRuleValidation<T> extends RuleRequestValidation<T> {

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

        DataAttributeDto attribute = (DataAttributeDto)this.config;

        Optional<ConfigDataTypeEnum> opType = ConfigDataTypeEnum.valueOfLabel(attribute.getData_type());
        if(opType.isEmpty()){
            throw new RequestValidationException( String.format( errorMessageTypeNotFound,attribute.getData_type()));
        }


        ConfigDataTypeEnum type =   (ConfigDataTypeEnum.valueOfLabel(attribute.getData_type())).get();

        if(ConfigDataTypeEnum.NUMBER.equals( type )){
            try {
                Double.parseDouble(value);
            }catch (NumberFormatException  e){
                throw new RequestValidationException( String.format( errorMessageDatePattern,attribute.getId_name(),value,type.getLabel()));
            }

        }else  if(ConfigDataTypeEnum.DATE.equals( type )){

            final var dateFormat = new SimpleDateFormat(datePattern);
            dateFormat.setLenient(false);
            try
            {
                dateFormat.parse(value);
            }catch (ParseException e){
                throw new RequestValidationException( String.format( errorMessageDatePattern,attribute.getId_name(),value,type.getLabel()));
            }
        }
    }
}
