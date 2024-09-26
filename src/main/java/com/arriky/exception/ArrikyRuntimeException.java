package com.arriky.exception;

/**
 * An customized exception that is thrown if any runtime error is occurred in the app.
 * @author Songyue Wang
 */
public class ArrikyRuntimeException extends Exception {
    public ArrikyRuntimeException(String message) {
        super(message);
    }
}
