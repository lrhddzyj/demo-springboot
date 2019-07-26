package com.lrh.exception;

public class RollBackException extends Exception {


    public RollBackException() {
    }

    public RollBackException(String message) {
        super(message);
    }

    public RollBackException(String message, Throwable cause) {
        super(message, cause);
    }

    public RollBackException(Throwable cause) {
        super(cause);
    }

    public RollBackException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
