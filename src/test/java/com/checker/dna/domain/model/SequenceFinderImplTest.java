package com.checker.dna.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;

class SequenceFinderImplTest {

  public static final int OCCURRENCE_NUMBER = 4;

  private final SequenceFinderImpl sequenceFinder = new SequenceFinderImpl(OCCURRENCE_NUMBER);

  @Test
  void shouldReturnEmptySequences() {
    final var humanSegments =
        new char[][] {
          {'A', 'T', 'G', 'C', 'G', 'A'},
          {'C', 'A', 'G', 'T', 'G', 'C'},
          {'T', 'T', 'A', 'T', 'T', 'T'},
          {'A', 'G', 'A', 'C', 'G', 'G'},
          {'G', 'C', 'C', 'T', 'C', 'A'},
          {'T', 'C', 'A', 'C', 'T', 'G'}
        };
    final var sequences = sequenceFinder.find(humanSegments);
    Assertions.assertTrue(sequences.isEmpty());
  }

  @Test
  void shouldReturn1SequenceWithAOccurrences() {
    final var humanSegments =
        new char[][] {
          {'A', 'T', 'G', 'C', 'G', 'A'},
          {'C', 'G', 'A', 'A', 'A', 'A'},
          {'T', 'T', 'A', 'T', 'T', 'T'},
          {'A', 'G', 'A', 'C', 'G', 'G'},
          {'G', 'C', 'C', 'T', 'C', 'A'},
          {'T', 'C', 'A', 'C', 'T', 'G'}
        };
    final var sequences = sequenceFinder.find(humanSegments);

    Assertions.assertEquals(1, sequences.size());

    final var expectSequence =
        new Sequence(
            asList(
                new Occurrence(1, 2, 'A'),
                new Occurrence(1, 3, 'A'),
                new Occurrence(1, 4, 'A'),
                new Occurrence(1, 5, 'A')));

    Assertions.assertEquals(expectSequence, sequences.get(0));
  }

  @Test
  void shouldReturn2SequencesWithAOccurrences() {
    final var simianSegments =
        new char[][] {
          {'A', 'T', 'A', 'C', 'G', 'A'},
          {'C', 'G', 'A', 'A', 'A', 'A'},
          {'T', 'T', 'A', 'T', 'T', 'T'},
          {'A', 'G', 'A', 'C', 'G', 'G'},
          {'G', 'C', 'C', 'T', 'C', 'A'},
          {'T', 'C', 'A', 'C', 'T', 'G'}
        };
    final var sequences = sequenceFinder.find(simianSegments);

    Assertions.assertEquals(2, sequences.size());

    final var expectedSequences =
        asList(
            new Sequence(
                asList(
                    new Occurrence(1, 2, 'A'),
                    new Occurrence(1, 3, 'A'),
                    new Occurrence(1, 4, 'A'),
                    new Occurrence(1, 5, 'A'))),
            new Sequence(
                asList(
                    new Occurrence(0, 2, 'A'),
                    new Occurrence(1, 2, 'A'),
                    new Occurrence(2, 2, 'A'),
                    new Occurrence(3, 2, 'A'))));

    Assertions.assertTrue(sequences.containsAll(expectedSequences));
  }

  @Test
  void shouldReturn1SequenceWithGOccurrences() {
    final var humanSegments =
        new char[][] {
          {'A', 'A', 'G', 'G', 'G', 'G'},
          {'C', 'A', 'G', 'T', 'G', 'C'},
          {'T', 'T', 'A', 'T', 'T', 'T'},
          {'A', 'G', 'A', 'C', 'G', 'G'},
          {'G', 'C', 'C', 'T', 'C', 'A'},
          {'T', 'C', 'A', 'C', 'T', 'G'}
        };
    final var sequences = sequenceFinder.find(humanSegments);

    Assertions.assertEquals(1, sequences.size());

    final var expectedSequence =
        new Sequence(
            asList(
                new Occurrence(0, 2, 'G'),
                new Occurrence(0, 3, 'G'),
                new Occurrence(0, 4, 'G'),
                new Occurrence(0, 5, 'G')));

    Assertions.assertEquals(expectedSequence, sequences.get(0));
  }

  @Test
  void shouldReturn3SequencesWithGAndTOccurrences() {
    final var simianSegments =
        new char[][] {
          {'A', 'T', 'G', 'G', 'G', 'G'},
          {'C', 'T', 'T', 'T', 'T', 'T'},
          {'T', 'T', 'A', 'T', 'T', 'T'},
          {'A', 'G', 'A', 'C', 'T', 'G'},
          {'G', 'C', 'C', 'T', 'C', 'A'},
          {'T', 'C', 'A', 'C', 'T', 'G'}
        };
    final var sequences = sequenceFinder.find(simianSegments);

    Assertions.assertEquals(3, sequences.size());

    final var expectedSequences =
        asList(
            new Sequence(
                asList(
                    new Occurrence(0, 2, 'G'),
                    new Occurrence(0, 3, 'G'),
                    new Occurrence(0, 4, 'G'),
                    new Occurrence(0, 5, 'G'))),
            new Sequence(
                asList(
                    new Occurrence(1, 1, 'T'),
                    new Occurrence(1, 2, 'T'),
                    new Occurrence(1, 3, 'T'),
                    new Occurrence(1, 4, 'T'))),
            new Sequence(
                asList(
                    new Occurrence(0, 1, 'T'),
                    new Occurrence(1, 2, 'T'),
                    new Occurrence(2, 3, 'T'),
                    new Occurrence(3, 4, 'T'))));

    Assertions.assertTrue(sequences.containsAll(expectedSequences));
  }

  @Test
  void shouldReturn2SequencesWithAOccurrencesWith8x8() {
    final var segments =
        new char[][] {
          {'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'},
          {'C', 'A', 'G', 'T', 'G', 'C', 'G', 'A'},
          {'T', 'T', 'A', 'T', 'T', 'T', 'A', 'T'},
          {'A', 'G', 'A', 'C', 'G', 'G', 'G', 'A'},
          {'G', 'C', 'C', 'T', 'C', 'A', 'A', 'T'},
          {'T', 'C', 'A', 'C', 'T', 'G', 'T', 'A'},
          {'T', 'G', 'A', 'C', 'T', 'G', 'A', 'T'},
          {'T', 'C', 'A', 'C', 'T', 'G', 'A', 'T'}
        };
    final var sequences = sequenceFinder.find(segments);

    Assertions.assertEquals(2, sequences.size());

    final var expectedSequences =
        asList(
            new Sequence(
                asList(
                    new Occurrence(0, 0, 'A'),
                    new Occurrence(0, 1, 'A'),
                    new Occurrence(0, 2, 'A'),
                    new Occurrence(0, 3, 'A'))),
            new Sequence(
                asList(
                    new Occurrence(0, 4, 'A'),
                    new Occurrence(0, 5, 'A'),
                    new Occurrence(0, 6, 'A'),
                    new Occurrence(0, 7, 'A'))));

    Assertions.assertTrue(sequences.containsAll(expectedSequences));
  }
}
