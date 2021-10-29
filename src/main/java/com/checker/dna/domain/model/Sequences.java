package com.checker.dna.domain.model;

import java.util.LinkedList;
import java.util.List;

public class Sequences {

  private final List<Sequence> sequences = new LinkedList<>();

  public void add(Sequence sequence) {
    this.sequences.add(sequence);
  }

  public boolean isEmpty() {
    return this.sequences.isEmpty();
  }
}
