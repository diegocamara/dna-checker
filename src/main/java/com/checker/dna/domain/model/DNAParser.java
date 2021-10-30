package com.checker.dna.domain.model;

import com.checker.dna.domain.exception.ItsNotAnSimianException;

public class DNAParser {

  private String[] segments;

  private DNAParser() {}

  public static DNAParser parser() {
    return new DNAParser();
  }

  public DNAParser withSegments(String[] segments) {
    this.segments = segments;
    return this;
  }

  public DNA parse() {
    try {
      return new SimianDNA(this.segments);
    } catch (ItsNotAnSimianException exception) {
      return new HumanDNA(this.segments);
    }
  }
}
