package com.checker.dna.domain.model;

public interface DNARepository {
  void storeIfNotExists(DNA dna);

  DNAStats retrieveStats();
}
