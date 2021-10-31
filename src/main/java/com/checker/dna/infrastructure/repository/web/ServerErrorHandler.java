package com.checker.dna.infrastructure.repository.web;

import com.checker.dna.domain.exception.DNAException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ServerErrorHandler {

  @ExceptionHandler(DNAException.class)
  public ResponseEntity<ErrorResponse> dnaExceptionHandler(DNAException dnaException) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(dnaException));
  }
}
