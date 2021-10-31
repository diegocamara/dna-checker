package com.checker.dna.domain.model;

import com.checker.dna.domain.exception.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;

class SimianDNATest {

  @Test
  void shouldThrowsNullDNAInputException() {
    // final var input = new String[] {"CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"};

    Assertions.assertThrows(NullDNAInputException.class, () -> new SimianDNA(null));
  }

  @Test
  void shouldThrowsEmptyDNAInputException() {

    final var input = new String[] {};

    Assertions.assertThrows(EmptyDNAInputException.class, () -> new SimianDNA(input));
  }

  @Test
  void shouldThrowsInvalidDNASegmentExceptionForNullSegment() {

    final var input = new String[] {null};

    final var invalidDNASegmentException =
        Assertions.assertThrows(InvalidDNASegmentException.class, () -> new SimianDNA(input));

    Assertions.assertNull(invalidDNASegmentException.getSegment());
  }

  @Test
  void shouldThrowsInvalidDNASegmentExceptionForEmptySegment() {

    final var emptySegment = "";

    final var input = new String[] {emptySegment};

    final var invalidDNASegmentException =
        Assertions.assertThrows(InvalidDNASegmentException.class, () -> new SimianDNA(input));

    Assertions.assertEquals(emptySegment, invalidDNASegmentException.getSegment());
  }

  @Test
  void shouldThrowsInvalidDNASegmentExceptionForWrongCharacterSegment() {

    final var oneCharacterSegment = "ABCDEF";

    final var input = new String[] {oneCharacterSegment};

    final var invalidDNASegmentException =
        Assertions.assertThrows(InvalidDNASegmentException.class, () -> new SimianDNA(input));

    Assertions.assertEquals(oneCharacterSegment, invalidDNASegmentException.getSegment());
  }

  @Test
  void shouldThrowsInvalidDNAOrderExceptionWhenInputLengthIs2() {

    final var input = new String[] {"CTGAGA", "CTATGC"};

    Assertions.assertThrows(InvalidDNAOrderException.class, () -> new SimianDNA(input));
  }

  @Test
  void shouldThrowsInvalidDNAOrderExceptionWhenInputLengthIs5() {

    final var input = new String[] {"CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCTA"};

    Assertions.assertThrows(InvalidDNAOrderException.class, () -> new SimianDNA(input));
  }

  @Test
  void shouldThrowsInvalidDNAOrderExceptionWhenInputLengthIs9() {

    final var input =
        new String[] {
          "CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCTA", "CCTAGC", "AATTGC", "TAGCTT", "TAGGTA"
        };

    Assertions.assertThrows(InvalidDNAOrderException.class, () -> new SimianDNA(input));
  }

  @Test
  void shouldThrowsItsNotAnSimianExceptionWhenValidDNA() {
    final var input = new String[] {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};

    Assertions.assertThrows(ItsNotAnSimianException.class, () -> new SimianDNA(input));
  }

  @Test
  void shouldCreateSimianDNAInstanceWith3Sequences() {
    //    "CTGAGA"
    //    "CTATGC"
    //    "TATTGT"
    //    "AGAGGG"
    //    "CCCCTA"
    //    "TCACTG"
    final var simianDNA =
        new SimianDNA(new String[] {"CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"});

    Assertions.assertEquals(3, simianDNA.sequences().size());

    final var expectSequence1 =
        new Sequence(
            asList(
                new Occurrence(3, 0, 'A'),
                new Occurrence(2, 1, 'A'),
                new Occurrence(1, 2, 'A'),
                new Occurrence(0, 3, 'A')));

    final var expectSequence2 =
        new Sequence(
            asList(
                new Occurrence(4, 0, 'C'),
                new Occurrence(4, 1, 'C'),
                new Occurrence(4, 2, 'C'),
                new Occurrence(4, 3, 'C')));

    final var expectSequence3 =
        new Sequence(
            asList(
                new Occurrence(0, 4, 'G'),
                new Occurrence(1, 4, 'G'),
                new Occurrence(2, 4, 'G'),
                new Occurrence(3, 4, 'G')));

    Assertions.assertTrue(
        simianDNA
            .sequences()
            .containsAll(asList(expectSequence1, expectSequence2, expectSequence3)));
  }

  @Test
  void shouldCreateSimianDNAInstanceWith3SequencesInLowerCase() {
    //    "CTGAGA"
    //    "CTATGC"
    //    "TATTGT"
    //    "AGAGGG"
    //    "CCCCTA"
    //    "TCACTG"
    final var simianDNA =
        new SimianDNA(new String[] {"ctgaga", "ctatgc", "tattgt", "agaggg", "ccccta", "tcactg"});

    Assertions.assertEquals(3, simianDNA.sequences().size());

    final var expectSequence1 =
        new Sequence(
            asList(
                new Occurrence(3, 0, 'A'),
                new Occurrence(2, 1, 'A'),
                new Occurrence(1, 2, 'A'),
                new Occurrence(0, 3, 'A')));

    final var expectSequence2 =
        new Sequence(
            asList(
                new Occurrence(4, 0, 'C'),
                new Occurrence(4, 1, 'C'),
                new Occurrence(4, 2, 'C'),
                new Occurrence(4, 3, 'C')));

    final var expectSequence3 =
        new Sequence(
            asList(
                new Occurrence(0, 4, 'G'),
                new Occurrence(1, 4, 'G'),
                new Occurrence(2, 4, 'G'),
                new Occurrence(3, 4, 'G')));

    Assertions.assertTrue(
        simianDNA
            .sequences()
            .containsAll(asList(expectSequence1, expectSequence2, expectSequence3)));
  }

  @Test
  void shouldThrowsItsNotAnSimianExceptionWhenTryingCreateWith1Sequence() {

    //    "ATGCGA"
    //    "CAGTGC"
    //    "TTATCT"
    //    "AGACGG"
    //    "GCCTCA"
    //    "TCACTG"

    Assertions.assertThrows(
        ItsNotAnSimianException.class,
        () ->
            new SimianDNA(
                new String[] {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCCTCA", "TCACTG"}));
  }

  @Test
  void shouldCreateSimianDNAInstanceWith2Sequences() {

    //    "ATGCGA"
    //    "CAGTGC"
    //    "TTATCA"
    //    "AGACAG"
    //    "GCCACA"
    //    "TCACTG"
    final var simianDNA =
        new SimianDNA(new String[] {"ATGCGA", "CAGTGC", "TTATCA", "AGACAG", "GCCACA", "TCACTG"});

    Assertions.assertEquals(2, simianDNA.sequences().size());
  }

  @Test
  void shouldCreateSimianDNAInstanceWith2DiagonalSequences() {

    //    "ATGCGA"
    //    "CAGAGC"
    //    "TTATTT"
    //    "GAAAGG"
    //    "ACGTCA"
    //    "TCACTG"

    final var simianDNA =
        new SimianDNA(new String[] {"ATGCGA", "CAGAGC", "TTATTT", "GAAAGG", "ACGTCA", "TCACTG"});

    final var sequences = simianDNA.sequences();

    Assertions.assertEquals(2, sequences.size());

    final var expectSequence1 =
        new Sequence(
            asList(
                new Occurrence(0, 0, 'A'),
                new Occurrence(1, 1, 'A'),
                new Occurrence(2, 2, 'A'),
                new Occurrence(3, 3, 'A')));

    final var expectSequence2 =
        new Sequence(
            asList(
                new Occurrence(4, 0, 'A'),
                new Occurrence(3, 1, 'A'),
                new Occurrence(2, 2, 'A'),
                new Occurrence(1, 3, 'A')));

    Assertions.assertTrue(sequences.containsAll(asList(expectSequence1, expectSequence2)));
  }
}
