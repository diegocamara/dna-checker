package com.checker.dna.domain.exception;

import static java.lang.String.format;

public class InvalidDNASegmentException extends RuntimeException {

  private final String segment;

  public InvalidDNASegmentException(String segment) {
    super(
        format(
            "DNA segment must contain [A,T,C,G] and 6 characters: Provided segment: %s", segment));
    this.segment = segment;
  }

  public String getSegment() {
    return segment;
  }
}
