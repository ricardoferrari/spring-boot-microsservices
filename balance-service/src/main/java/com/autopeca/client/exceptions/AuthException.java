package com.autopeca.client.exceptions;

public class AuthException extends Exception{
    /**
     * @param errorMessage
     */
    public AuthException(String errorMessage) {
        super(errorMessage);
    }
}
