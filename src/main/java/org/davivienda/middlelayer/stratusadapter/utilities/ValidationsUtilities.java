package org.davivienda.middlelayer.stratusadapter.utilities;

import org.davivienda.middlelayer.stratusadapter.services.validations.IStratusAdapterValidation;

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
