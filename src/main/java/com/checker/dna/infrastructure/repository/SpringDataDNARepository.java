package com.checker.dna.infrastructure.repository;

import com.checker.dna.domain.model.DNA;
import com.checker.dna.domain.model.DNARepository;
import com.checker.dna.domain.model.DNAStats;
import com.checker.dna.infrastructure.repository.springdata.SpringDataJpaDNARepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class SpringDataDNARepository implements DNARepository {

  private final SpringDataJpaDNARepository springDataJpaDNARepository;

  @Override
  public void storeIfNotExists(DNA result) {}

  @Override
  public DNAStats retrieveStats() {
    return null;
  }
}
