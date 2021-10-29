package com.checker.dna.domain.exception;

public class EmptyDNAInputException extends RuntimeException {

  public EmptyDNAInputException() {
    super("input must not be empty");
  }
}
