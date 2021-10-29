package com.checker.dna.domain.exception;

public class InvalidDNAOrderException extends RuntimeException {

  public InvalidDNAOrderException() {
    super("The DNA table must meet the NxN standard.");
  }
}
