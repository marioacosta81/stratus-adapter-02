package org.davivienda.middlelayer.stratusadapter.utilities;

import org.davivienda.middlelayer.stratusadapter.services.validations.RuleRequestValidation;

import java.util.Arrays;

public class ValidationsUtilities {

    public static void WeftRequestValidationsExecute(RuleRequestValidation... toValidate){
        Arrays.asList(toValidate).forEach(validation -> {
            validation.validate( );
        });
    }

    public static void WeftRequestValidationsExecute(RuleRequestValidation toValidate){
        toValidate.validate( );
    }

}
