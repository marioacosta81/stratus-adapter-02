package org.davivienda.middlelayer.stratusadapter.model.exceptions;

import java.io.Serializable;

public class StratusAdapterException extends
        RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public StratusAdapterException() {
    }

    public StratusAdapterException(String message) {
        super(message);
    }

    public StratusAdapterException(String message, Throwable cause) {
        super(message, cause);
    }

    public StratusAdapterException(Throwable cause) {
        super(cause);
    }

    public StratusAdapterException(String message, Throwable cause,
                           boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
