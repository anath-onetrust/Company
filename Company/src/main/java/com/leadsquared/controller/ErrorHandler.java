package com.leadsquared.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);
    private static final String LOG_MSG = "Error handled for: {}";

    /**
     * show validation messages
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public HttpEntity springValidationException(MethodArgumentNotValidException ex, WebRequest request) {
        LOGGER.error(LOG_MSG, ex);
        String msg = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(p -> String.format("%s : %s", p.getField(), p.getDefaultMessage()))
                .collect(Collectors.joining(", "));
        return getResponseEntity(HttpStatus.BAD_REQUEST, msg);
    }

    private static ResponseEntity getResponseEntity(HttpStatus status, String customMessage) {
        return ResponseEntity
                .status(status.value())
                .body(getBody(customMessage));
    }

    private static Map<String, Object> getBody(String customMessage) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("timestamp", new Date().getTime());
        response.put("message", "Internal Server Error please try again after sometime");
        return response;
    }
    @ResponseBody
    @ExceptionHandler({HttpServerErrorException.InternalServerError.class})
    public HttpEntity<String> error(HttpServerErrorException.InternalServerError ex, WebRequest request){
        LOGGER.error(LOG_MSG, ex);
        return getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }
    @ResponseBody
    @ExceptionHandler({Exception.class})
    public HttpEntity<String> genericError(Exception ex, WebRequest request){
        LOGGER.error(LOG_MSG, ex);
        return getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }
}