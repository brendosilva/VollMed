package com.alura.medVoll.api.domain.exceptions;

public class ValidacaoException extends RuntimeException{
    private String message ;
    public ValidacaoException(String message) {
        super(message);
    }

}
