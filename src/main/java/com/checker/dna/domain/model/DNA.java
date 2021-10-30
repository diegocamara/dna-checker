package com.checker.dna.domain.model;

import com.checker.dna.domain.exception.EmptyDNAInputException;
import com.checker.dna.domain.exception.InvalidDNAOrderException;
import com.checker.dna.domain.exception.InvalidDNASegmentException;
import com.checker.dna.domain.exception.NullDNAInputException;

public abstract class DNA {

  private final String[] segments;

  public DNA(String[] segments) {
    checkNull(segments);
    checkEmpty(segments);
    validDNA(resolveSegments(segments));
    this.segments = segments;
  }

  public abstract void validDNA(char[][] segments);

  private char[][] resolveSegments(String[] input) {
    final var segmentsLength = input.length;
    final var segments = new char[segmentsLength][segmentsLength];
    for (int index = 0; index < segmentsLength; index++) {
      final var segment = input[index];
      checkSegmentContent(segment);
      checkSegmentOrder(segment, segmentsLength);
      segments[index] = segment.toUpperCase().toCharArray();
    }
    return segments;
  }

  private void checkSegmentOrder(String segment, int segmentsLength) {
    if (segment.length() != segmentsLength) {
      throw new InvalidDNAOrderException();
    }
  }

  private void checkSegmentContent(String segment) {
    if (segment == null || !segment.matches("[ATCGatcg]+")) {
      throw new InvalidDNASegmentException(segment);
    }
  }

  private void checkNull(String[] input) {
    if (inputIsNull(input)) {
      throw new NullDNAInputException();
    }
  }

  private void checkEmpty(String[] input) {
    if (inputIsEmpty(input)) {
      throw new EmptyDNAInputException();
    }
  }

  private boolean inputIsEmpty(String[] input) {
    return input.length == 0;
  }

  private boolean inputIsNull(String[] input) {
    return input == null;
  }

  public String[] segments() {
    return this.segments;
  }
}
