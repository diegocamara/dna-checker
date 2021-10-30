package com.checker.dna.domain.feature.impl;

import com.checker.dna.domain.model.DNARepository;
import com.checker.dna.domain.model.HumanDNA;
import com.checker.dna.domain.model.SimianDNA;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class VerifyDNAImplTest {

  private DNARepository dnaRepository;
  private VerifyDNAImpl verifyDNA;

  @BeforeEach
  public void beforeEach() {
    this.dnaRepository = Mockito.mock(DNARepository.class);
    this.verifyDNA = new VerifyDNAImpl(this.dnaRepository);
  }

  @Test
  void shouldVerifySimianDNAAndStoreInDatabase() {
    final var segments = new String[] {"CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"};
    final var result = this.verifyDNA.handle(segments);
    Assertions.assertThat(result).isInstanceOf(SimianDNA.class);
    Assertions.assertThat(result.segments()).isEqualTo(segments);
    Mockito.verify(this.dnaRepository, Mockito.times(1)).storeIfNotExists(result);
  }

  @Test
  void shouldVerifyHumanDNAAndStoreInDatabase() {
    final var segments = new String[] {"ATGCGA", "CAGTGC", "TTATCT", "AGACGG", "GCCTCA", "TCACTG"};
    final var result = this.verifyDNA.handle(segments);
    Assertions.assertThat(result).isInstanceOf(HumanDNA.class);
    Assertions.assertThat(result.segments()).isEqualTo(segments);
    Mockito.verify(this.dnaRepository, Mockito.times(1)).storeIfNotExists(result);
  }
}
