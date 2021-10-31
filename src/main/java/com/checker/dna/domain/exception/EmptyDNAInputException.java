package com.checker.dna.domain.exception;

public class EmptyDNAInputException extends DNAException {

  public EmptyDNAInputException() {
    super("input must not be empty");
  }
}
