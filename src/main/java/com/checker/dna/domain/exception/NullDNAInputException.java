package com.checker.dna.domain.exception;

public class NullDNAInputException extends DNAException {

  public NullDNAInputException() {
    super("input must not be null");
  }
}
