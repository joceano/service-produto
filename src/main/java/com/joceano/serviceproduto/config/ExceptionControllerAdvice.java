package com.joceano.serviceproduto.config;

import com.joceano.serviceproduto.http.data.response.Error;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.NoResultException;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @Value("${springdoc.swagger-ui.path}")
    private String urlDocumentation;

    @Hidden
    @ResponseBody
    @ExceptionHandler(NoResultException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error noResult(NoResultException e) {
        return new Error("X_100", e.getMessage(), urlDocumentation);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error MethodArgument(MethodArgumentTypeMismatchException e) {
        return new Error("X_200", e.getMessage(), urlDocumentation);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error MethodArgument(MethodArgumentNotValidException exception) {
        String mensagem = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                .collect(Collectors.joining());
        return new Error("X_200", mensagem, urlDocumentation);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error other(Exception e) {
        return new Error("X_300", e.getMessage(), urlDocumentation);
    }

}
