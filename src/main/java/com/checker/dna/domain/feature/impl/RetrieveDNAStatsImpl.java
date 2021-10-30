package com.checker.dna.domain.feature.impl;

import com.checker.dna.domain.feature.RetrieveDNAStats;
import com.checker.dna.domain.model.DNARepository;
import com.checker.dna.domain.model.DNAStats;
import lombok.AllArgsConstructor;

import javax.inject.Named;

@Named
@AllArgsConstructor
public class RetrieveDNAStatsImpl implements RetrieveDNAStats {

  private final DNARepository dnaRepository;

  @Override
  public DNAStats handle() {
    return this.dnaRepository.retrieveStats();
  }
}
