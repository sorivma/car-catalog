package com.example.carcatalog.controller.advice;

import com.example.carcatalog.except.ClientErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Global exception handler for the application.
 * <p>
 *     This class is used to handle exceptions thrown by the application.
 *     <br>
 *     The exceptions are:
 *     <ul>
 *         <li>{@link ClientErrorException.EntityNotFoundException}</li>
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handles {@link ClientErrorException.EntityNotFoundException}
     * @param e the exception
     * @param model the model
     * @return the error page and 404 status code
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ClientErrorException.EntityNotFoundException.class)
    public String handleException(ClientErrorException e, Model model) {
        model.addAttribute("responseCode", 404);
        model.addAttribute("exceptionDetails", e.getMessage());
        return "error_page";
    }
}
