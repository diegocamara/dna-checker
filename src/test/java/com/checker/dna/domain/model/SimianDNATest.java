package com.checker.dna.domain.model;

import com.checker.dna.domain.exception.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SimianDNATest {

  @Test
  void shouldThrowsNullDNAInputException() {
    // final var input = new String[] {"CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCTA",
    // "TCACTG"};

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

  //  @Test
  //  void shouldThrowsInvalidDNASegmentExceptionForOneCharacterSegment() {
  //
  //    final var oneCharacterSegment = "A";
  //
  //    final var input = new String[] {oneCharacterSegment};
  //
  //    final var invalidDNASegmentException =
  //        Assertions.assertThrows(InvalidDNASegmentException.class, () -> new SimianDNA(input));
  //
  //    Assertions.assertEquals(oneCharacterSegment, invalidDNASegmentException.getSegment());
  //  }

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
  void shouldCreateSimianDNAInstance() {
    final var simianDNA =
        new SimianDNA(new String[] {"CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"});
  }
}
