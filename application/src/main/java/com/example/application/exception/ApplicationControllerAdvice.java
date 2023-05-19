package com.example.application.exception;

import com.example.core.exception.DuplicatePersonEmailException;
import com.example.core.exception.PersonNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class ApplicationControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ProblemDetail handleDuplicatePersonEmailException(DuplicatePersonEmailException exception) {
        log.info("DuplicatePersonEmailException: {}", exception.getMessage());
        var pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setDetail(exception.getMessage());
        return pd;
    }

    @ExceptionHandler
    public ProblemDetail handlePersonNotFoundException(PersonNotFoundException exception) {
        log.info("PersonNotFoundException: {}", exception.getMessage());
        var pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pd.setDetail(exception.getMessage());
        return pd;
    }

    @ExceptionHandler
    public ProblemDetail handleDefault(Exception exception) {
        log.error("Exception: {}", exception.getMessage());
        var pd = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pd.setDetail("An unexpected error occurred.");
        return pd;
    }

}
