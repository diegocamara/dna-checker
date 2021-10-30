package com.checker.dna.domain.model;

import com.checker.dna.domain.exception.ItsNotAnSimianException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class SimianDNA extends DNA {

  public static final int OCCURRENCE_NUMBER = 4;
  public static final int MINIMAL_SEQUENCES = 2;

  private List<Sequence> sequences;

  public SimianDNA(String[] input) {
    super(input);
  }

  @Override
  public void validDNA(char[][] segments) {
    final SequenceFinder sequenceFinder = new SequenceFinderImpl(OCCURRENCE_NUMBER);
    final var resultSequences = sequenceFinder.find(segments);
    if (resultSequences.isEmpty() || resultSequences.size() < MINIMAL_SEQUENCES) {
      throw new ItsNotAnSimianException();
    }
    this.sequences = resultSequences;
  }

  public List<Sequence> sequences() {
    return this.sequences;
  }
}
