package com.checker.dna.infrastructure.repository;

import com.checker.dna.domain.model.DNA;
import com.checker.dna.domain.model.DNARepository;
import com.checker.dna.domain.model.DNAStats;
import com.checker.dna.domain.model.SimianDNA;
import com.checker.dna.infrastructure.repository.springdata.SpringDataJpaDNARepository;
import com.checker.dna.infrastructure.repository.springdata.entity.DNAEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class SpringDataDNARepository implements DNARepository {

  private final SpringDataJpaDNARepository springDataJpaDNARepository;
  private final ObjectMapper objectMapper;

  @Override
  public void storeIfNotExists(DNA dna) {
    final var dnaId = writeValueAsString(dna.segments());

    if (!springDataJpaDNARepository.existsById(dnaId)) {

      final var dnaEntity = new DNAEntity(dnaId, from(dna));

      this.springDataJpaDNARepository.save(dnaEntity);
    }
  }

  @Override
  public DNAStats retrieveStats() {
    final var humanDNACount =
        this.springDataJpaDNARepository.count(
            Example.of(new DNAEntity(null, DNAEntity.DNAType.HUMAN)));
    final var simianDNACount =
        this.springDataJpaDNARepository.count(
            Example.of(new DNAEntity(null, DNAEntity.DNAType.SIMIAN)));
    return new DNAStats(humanDNACount, simianDNACount);
  }

  private DNAEntity.DNAType from(DNA dna) {
    return dna instanceof SimianDNA ? DNAEntity.DNAType.SIMIAN : DNAEntity.DNAType.HUMAN;
  }

  @SneakyThrows
  private String writeValueAsString(Object value) {
    return this.objectMapper.writeValueAsString(value);
  }
}
