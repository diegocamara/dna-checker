package com.checker.dna.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
@EqualsAndHashCode
public class Sequence {

  private final List<Occurrence> occurrences;

  public Sequence(List<Occurrence> occurrences) {
    this.occurrences = new LinkedList<>(occurrences);
  }
}
