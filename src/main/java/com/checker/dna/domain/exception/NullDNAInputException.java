package com.checker.dna.domain.exception;

public class NullDNAInputException extends RuntimeException {

  public NullDNAInputException() {
    super("input must not be null");
  }
}
