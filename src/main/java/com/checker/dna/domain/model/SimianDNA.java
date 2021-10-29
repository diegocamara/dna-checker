package com.checker.dna.domain.model;

import com.checker.dna.domain.exception.ItsNotAnSimianException;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;

public class SimianDNA extends DNA {

  public static final int OCCURRENCE_NUMBER = 4;

  public SimianDNA(String[] input) {
    super(input);
  }

  @Override
  public void validDNA(char[][] segments) {

    final var resultSequences = new LinkedList<Sequence>();

    resultSequences.addAll(
        findSequences(
            segments,
            (lineIndex, columnIndex) ->
                new Occurrence(lineIndex, columnIndex, segments[lineIndex][columnIndex])));
    resultSequences.addAll(
        findSequences(
            segments,
            (lineIndex, columnIndex) ->
                new Occurrence(columnIndex, lineIndex, segments[columnIndex][lineIndex])));

    if (resultSequences.isEmpty()) {
      throw new ItsNotAnSimianException();
    }
  }

  private List<Sequence> findSequences(
      char[][] segments, BiFunction<Integer, Integer, Occurrence> occurrenceBiFunction) {

    final var sequences = new LinkedList<Sequence>();

    for (int lineIndex = 0; lineIndex < segments.length; lineIndex++) {

      final var occurrences = new LinkedList<Occurrence>();

      Occurrence lastOccurrence = null;

      for (int columnIndex = 0; columnIndex < segments.length; columnIndex++) {

        final var current = occurrenceBiFunction.apply(lineIndex, columnIndex);

        if (lastOccurrence == null || lastOccurrence.getValue() == current.getValue()) {
          occurrences.add(current);
          if (occurrences.size() == OCCURRENCE_NUMBER) {
            sequences.add(new Sequence(occurrences));
          }
        } else {
          occurrences.clear();
        }
        lastOccurrence = current;
      }
    }

    return sequences;
  }
}
