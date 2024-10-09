package com.aka.simplebootstrap.controller;

import com.aka.simplebootstrap.controller.exception.BookIdMismatchException;
import com.aka.simplebootstrap.controller.exception.BookNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ BookNotFoundException.class })
    public ProblemDetail  handleNotFound(Exception ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
        problemDetail.setTitle("Book not Found");
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setInstance(URI.create("api/books"));
        return problemDetail;
    }

    @ExceptionHandler({ BookIdMismatchException.class,
            ConstraintViolationException.class,
            DataIntegrityViolationException.class })
    public ProblemDetail handleBadRequest(Exception ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                ex.getLocalizedMessage()
        );
        problemDetail.setInstance(URI.create("api/books/"));
        problemDetail.setTitle("BAD REQUEST");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}
