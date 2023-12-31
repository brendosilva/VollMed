package com.alura.medVoll.api.infra.exceptions;

import com.alura.medVoll.api.domain.exceptions.ValidacaoException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class TratadorErros {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity tratamento404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratamento400(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors();
        return ResponseEntity.badRequest().body(
                errors.stream().map(DadosErroValidacao::new).toList()
        );
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity trataErrorRegraDeNegocio(ValidacaoException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    private record DadosErroValidacao(String campo, String message)     {
        public DadosErroValidacao(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

}
