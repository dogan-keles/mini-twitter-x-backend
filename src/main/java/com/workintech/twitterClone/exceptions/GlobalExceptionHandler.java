package com.workintech.twitterClone.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<TwitterErrorResponse> handleException(TwitterException twitterException) {
        TwitterErrorResponse twitterErrorResponse = new TwitterErrorResponse(
                twitterException.getHttpStatus().value(), twitterException.getMessage(), LocalDateTime.now()
        );
        return new ResponseEntity<>(twitterErrorResponse, twitterException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<TwitterErrorResponse> handleException(Exception exception) {
        TwitterErrorResponse twitterErrorResponse = new TwitterErrorResponse(
                HttpStatus.BAD_REQUEST.value(), exception.getMessage(), LocalDateTime.now()
        );
        log.error("Exception occured ... " + exception.getMessage());
        return new ResponseEntity<>(twitterErrorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public ResponseEntity<TwitterErrorResponse> handleBindExceptions(MethodArgumentNotValidException exception){
        List<Map<String, String>> errorList = exception.getBindingResult().getFieldErrors()
                .stream().map(fieldError -> {
                    log.error(fieldError.getField() + ": " + fieldError.getDefaultMessage());
                    Map<String, String> errors = new HashMap<>();
                    errors.put(fieldError.getField(), fieldError.getDefaultMessage());
                    return errors;
                }).collect(Collectors.toList());

        StringBuilder errorMessage = new StringBuilder();
        for (Map<String, String> map : errorList) {
            for (String message : map.values()) {
                if (errorMessage.length() > 0) {
                    errorMessage.append(", ");
                }
                errorMessage.append(message);
            }
        }

        TwitterErrorResponse twitterErrorResponse = new TwitterErrorResponse(HttpStatus.BAD_REQUEST.value(), errorMessage.toString(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(twitterErrorResponse);
    }


}
