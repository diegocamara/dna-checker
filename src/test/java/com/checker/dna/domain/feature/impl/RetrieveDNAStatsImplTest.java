package com.checker.dna.domain.feature.impl;

import com.checker.dna.domain.model.DNARepository;
import com.checker.dna.domain.model.DNAStats;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RetrieveDNAStatsImplTest {

  private DNARepository dnaRepository;
  private RetrieveDNAStatsImpl retrieveDNAStats;

  @BeforeEach
  public void beforeEach() {
    this.dnaRepository = Mockito.mock(DNARepository.class);
    this.retrieveDNAStats = new RetrieveDNAStatsImpl(this.dnaRepository);
  }

  @Test
  void shouldRetrieveStatsWith100HumanDNACountAnd40SimianDNACount() {
    long humanDNACount = 100;
    long simianDNACount = 40;
    final var dnaStats = new DNAStats(humanDNACount, simianDNACount);
    Mockito.when(this.dnaRepository.retrieveStats()).thenReturn(dnaStats);
    final var result = this.retrieveDNAStats.handle();
    Assertions.assertNotNull(result);
    Assertions.assertEquals(dnaStats, result);
    Mockito.verify(this.dnaRepository, Mockito.times(1)).retrieveStats();
  }
}
