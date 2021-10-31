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
  void shouldReturn2SequenceWithAOccurrences() {
    final var humanSegments =
        new char[][] {
          {'A', 'T', 'A', 'C', 'G', 'A'},
          {'C', 'G', 'A', 'A', 'A', 'A'},
          {'T', 'T', 'A', 'T', 'T', 'T'},
          {'A', 'G', 'A', 'C', 'G', 'G'},
          {'G', 'C', 'C', 'T', 'C', 'A'},
          {'T', 'C', 'A', 'C', 'T', 'G'}
        };
    final var sequences = sequenceFinder.find(humanSegments);

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
}
