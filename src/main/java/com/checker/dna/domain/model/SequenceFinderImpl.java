package com.checker.dna.domain.model;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;

public class SequenceFinderImpl implements SequenceFinder {

  private final int occurrenceNumber;

  public SequenceFinderImpl(int occurrenceNumber) {
    this.occurrenceNumber = occurrenceNumber;
  }

  @Override
  public List<Sequence> find(char[][] segments) {
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
    resultSequences.addAll(findDiagonallyTopDownLeftToRight(segments));
    resultSequences.addAll(findDiagonallyBottomUpLeftToRight(segments));
    return resultSequences;
  }

  private List<Sequence> findSequences(
      char[][] segments, BiFunction<Integer, Integer, Occurrence> occurrenceBiFunction) {

    final var result = new LinkedList<Sequence>();

    for (int lineIndex = 0; lineIndex < segments.length; lineIndex++) {

      final var occurrences = new LinkedList<Occurrence>();

      Occurrence lastOccurrence = null;

      for (int columnIndex = 0; columnIndex < segments.length; columnIndex++) {

        final var current = occurrenceBiFunction.apply(lineIndex, columnIndex);

        verifySequence(result, occurrences, lastOccurrence, current);

        lastOccurrence = current;
      }
    }

    return result;
  }

  private List<Sequence> findDiagonallyTopDownLeftToRight(char[][] segments) {

    final var segmentsLength = segments.length;
    final var diagonalLinesNumber = (2 * segmentsLength) - 1;
    final var centerPoint = (diagonalLinesNumber / 2) + 1;
    int diagonalItemsNumber = 0;

    final var resultSequences = new LinkedList<Sequence>();

    for (int i = 1; i <= diagonalLinesNumber; i++) {

      int rowIndex;
      int columnIndex;

      final var occurrences = new LinkedList<Occurrence>();
      Occurrence lastOccurrence = null;

      if (i <= centerPoint) {
        diagonalItemsNumber++;
        for (int j = 0; j < diagonalItemsNumber; j++) {
          rowIndex = (i - j) - 1;
          columnIndex = j;

          final var current =
              new Occurrence(rowIndex, columnIndex, segments[rowIndex][columnIndex]);

          verifySequence(resultSequences, occurrences, lastOccurrence, current);
          lastOccurrence = current;
        }

      } else {
        diagonalItemsNumber--;
        for (int j = 0; j < diagonalItemsNumber; j++) {
          rowIndex = (segmentsLength - 1) - j;
          columnIndex = (i - segmentsLength) + j;
          final var current =
              new Occurrence(rowIndex, columnIndex, segments[rowIndex][columnIndex]);
          verifySequence(resultSequences, occurrences, lastOccurrence, current);
          lastOccurrence = current;
        }
      }
    }

    return resultSequences;
  }

  private List<Sequence> findDiagonallyBottomUpLeftToRight(char[][] segments) {

    final var segmentsLength = segments.length;
    final var diagonalLinesNumber = (2 * segmentsLength) - 1;
    final var centerPoint = (diagonalLinesNumber / 2) + 1;
    int diagonalItemsNumber = 0;

    final var resultSequences = new LinkedList<Sequence>();

    for (int i = 1; i <= diagonalLinesNumber; i++) {

      int rowIndex;
      int columnIndex;

      final var occurrences = new LinkedList<Occurrence>();
      Occurrence lastOccurrence = null;

      if (i <= centerPoint) {
        diagonalItemsNumber++;

        for (int j = 0; j < diagonalItemsNumber; j++) {
          rowIndex = (segmentsLength - diagonalItemsNumber) + j;
          columnIndex = j;
          final var current =
              new Occurrence(rowIndex, columnIndex, segments[rowIndex][columnIndex]);
          verifySequence(resultSequences, occurrences, lastOccurrence, current);
          lastOccurrence = current;
        }

      } else {
        diagonalItemsNumber--;

        for (int j = 0; j < diagonalItemsNumber; j++) {
          rowIndex = j;
          columnIndex = (segmentsLength - diagonalItemsNumber) + j;
          final var current =
              new Occurrence(rowIndex, columnIndex, segments[rowIndex][columnIndex]);
          verifySequence(resultSequences, occurrences, lastOccurrence, current);
          lastOccurrence = current;
        }
      }
    }

    return resultSequences;
  }

  private void verifySequence(
      LinkedList<Sequence> resultSequences,
      LinkedList<Occurrence> occurrences,
      Occurrence lastOccurrence,
      Occurrence current) {

    if (lastOccurrence != null && lastOccurrence.getValue() != current.getValue()) {
      occurrences.clear();
    }

    occurrences.add(current);

    if (occurrences.size() == this.occurrenceNumber) {
      resultSequences.add(new Sequence(occurrences));
      occurrences.clear();
    }
  }
}
