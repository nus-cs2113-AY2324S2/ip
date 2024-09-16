package nyanbot.exception;

import java.lang.Exception;

public class NyanException extends Exception {
    public NyanException(String errorMessage) {
        super(errorMessage);
    }
}
