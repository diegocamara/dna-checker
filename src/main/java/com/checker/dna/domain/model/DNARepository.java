package com.checker.dna.domain.model;

public interface DNARepository {
  void storeIfNotExists(DNA result);
}
