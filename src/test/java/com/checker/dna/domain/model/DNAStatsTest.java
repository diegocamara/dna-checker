package com.checker.dna.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DNAStatsTest {

  @Test
  void shouldReturnDNAStatsWithRatio0_4() {
    final var humanDNACount = 100;
    final var simianDNACount = 40;
    final var result = new DNAStats(humanDNACount, simianDNACount);
    Assertions.assertEquals(0.4f, result.ratio());
  }

  @Test
  void shouldReturnDNAStatsWithRatio10() {
    final var humanDNACount = 0;
    final var simianDNACount = 10;
    final var result = new DNAStats(humanDNACount, simianDNACount);
    Assertions.assertEquals(10f, result.ratio());
  }

  @Test
  void shouldReturnDNAStatsWithRatio0_2() {
    final var humanDNACount = 100;
    final var simianDNACount = 20;
    final var result = new DNAStats(humanDNACount, simianDNACount);
    Assertions.assertEquals(0.2f, result.ratio());
  }
}
