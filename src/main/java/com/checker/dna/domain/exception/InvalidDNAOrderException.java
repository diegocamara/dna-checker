package com.checker.dna.domain.exception;

public class InvalidDNAOrderException extends DNAException {

  public InvalidDNAOrderException() {
    super("The DNA table must meet the NxN standard.");
  }
}
