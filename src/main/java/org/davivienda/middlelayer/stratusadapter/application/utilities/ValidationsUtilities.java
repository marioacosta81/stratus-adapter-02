package org.davivienda.middlelayer.stratusadapter.application.utilities;

import org.davivienda.middlelayer.stratusadapter.application.services.validations.IStratusAdapterValidation;

import java.util.Arrays;

public class ValidationsUtilities {

    public static void WeftRequestValidationsExecute(IStratusAdapterValidation... toValidate){
        Arrays.asList(toValidate).forEach(validation -> {
            validation.validate( );
        });
    }

    public static void WeftRequestValidationsExecute(IStratusAdapterValidation toValidate){
        toValidate.validate( );
    }

}
