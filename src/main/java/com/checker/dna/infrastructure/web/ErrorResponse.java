package com.checker.dna.infrastructure.web;

import com.checker.dna.domain.exception.DNAException;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse {

  private String message;

  public ErrorResponse(DNAException dnaException) {
    this.message = dnaException.getMessage();
  }
}
