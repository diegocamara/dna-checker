package com.checker.dna.domain.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.checker.dna.domain.model.DNAParser.parser;

class DNAParserTest {

  @Test
  void shouldParseSimianDNA() {
    final var segments = new String[] {"CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"};
    final var result = parser().withSegments(segments).parse();
    Assertions.assertThat(result).isInstanceOf(SimianDNA.class);
  }

  @Test
  void shouldParseHumanDNA() {
    final var segments = new String[] {"ATGCGA", "CAGTGC", "TTATCT", "AGACGG", "GCCTCA", "TCACTG"};
    final var result = parser().withSegments(segments).parse();
    Assertions.assertThat(result).isInstanceOf(HumanDNA.class);
  }
}
