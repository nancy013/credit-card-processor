package com.sapient.creditcardprocessor.exception;

import com.sapient.creditcardprocessor.adapter.in.web.resource.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class CreditCardControllerAdvice {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {

    final List<ErrorResponse.Error> errors = exception.getBindingResult().getFieldErrors().stream()
      .map(ex -> ErrorResponse.Error.builder()
        .field(ex.getField())
        .errorMessage(ex.getDefaultMessage())
        .build())
      .collect(Collectors.toList());

    log.error("Error: {}", errors);

    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(ErrorResponse.builder()
        .errors(errors)
        .build());
  }

  @ExceptionHandler(MissingRequestHeaderException.class)
  protected ResponseEntity<ErrorResponse> handleMissingRequestHeaderException(final MissingRequestHeaderException exception) {

    final List<ErrorResponse.Error> errors = List.of(ErrorResponse.Error.builder()
      .field(exception.getHeaderName())
      .errorMessage(exception.getMessage())
      .build());

    log.error("Error: {}", errors);

    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(ErrorResponse.builder()
        .errors(errors)
        .build());
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  protected ResponseEntity<ErrorResponse> handleMissingRequestHeaderException(final DataIntegrityViolationException exception) {

    final List<ErrorResponse.Error> errors = List.of(ErrorResponse.Error.builder()
      .errorMessage(exception.getMessage())
      .build());

    log.error("Error: {}", errors);

    return ResponseEntity
      .status(HttpStatus.UNPROCESSABLE_ENTITY)
      .body(ErrorResponse.builder()
        .errors(errors)
        .build());
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<ErrorResponse> handleMissingRequestHeaderException(final Exception exception) {

    final List<ErrorResponse.Error> errors = List.of(ErrorResponse.Error.builder()
      .errorMessage(exception.getMessage())
      .build());

    log.error("Error: {}", errors);

    return ResponseEntity
      .status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(ErrorResponse.builder()
        .errors(errors)
        .build());
  }
}
