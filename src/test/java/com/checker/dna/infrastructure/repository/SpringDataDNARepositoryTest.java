package com.checker.dna.infrastructure.repository;

import com.checker.dna.domain.model.SimianDNA;
import com.checker.dna.infrastructure.repository.springdata.SpringDataJpaDNARepository;
import com.checker.dna.infrastructure.repository.springdata.entity.DNAEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.SpyBean;

@DataJpaTest
class SpringDataDNARepositoryTest {

  private final ObjectMapper utilsObjectMapper = new ObjectMapper();

  @SpyBean private SpringDataJpaDNARepository springDataJpaDNARepository;
  @Autowired private TestEntityManager testEntityManager;
  @SpyBean private ObjectMapper objectMapper;
  private SpringDataDNARepository springDataDNARepository;

  @BeforeEach
  public void beforeEach() {
    this.springDataDNARepository =
        new SpringDataDNARepository(this.springDataJpaDNARepository, this.objectMapper);
  }

  @AfterEach
  public void afterEach() {
    Mockito.reset(this.springDataJpaDNARepository, this.objectMapper);
    clearDB();
  }

  private void clearDB() {
    this.testEntityManager
        .getEntityManager()
        .createNativeQuery("DELETE FROM DNA_TB;")
        .executeUpdate();
  }

  @Test
  void shouldPersistSimianDNA() throws JsonProcessingException {

    final var segments = new String[] {"CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"};

    final var simianDNA = new SimianDNA(segments);

    final var simianDNAId = writeValueAsString(segments);

    this.springDataDNARepository.storeIfNotExists(simianDNA);

    final var persistedSimianDNA = this.testEntityManager.find(DNAEntity.class, simianDNAId);

    Assertions.assertNotNull(persistedSimianDNA);
    Assertions.assertEquals(simianDNAId, persistedSimianDNA.getId());
    Mockito.verify(objectMapper, Mockito.times(1)).writeValueAsString(segments);
    Mockito.verify(this.springDataJpaDNARepository, Mockito.times(1)).existsById(simianDNAId);
    Mockito.verify(this.springDataJpaDNARepository, Mockito.times(1))
        .save(Mockito.any(DNAEntity.class));
  }

  @Test
  void shouldNotPersistExistingSimianDNA() throws JsonProcessingException {

    final var segments = new String[] {"CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"};

    final var simianDNAId = writeValueAsString(segments);

    this.testEntityManager.persistAndFlush(new DNAEntity(simianDNAId, DNAEntity.DNAType.SIMIAN));

    final var simianDNA = new SimianDNA(segments);

    this.springDataDNARepository.storeIfNotExists(simianDNA);
    Mockito.verify(this.objectMapper, Mockito.times(1)).writeValueAsString(segments);
    Mockito.verify(this.springDataJpaDNARepository, Mockito.times(1)).existsById(simianDNAId);
    Mockito.verify(this.springDataJpaDNARepository, Mockito.times(0)).save(Mockito.any());
  }

  @Test
  void shouldReturnStatsWithSimianDNACount0AndHumanDNACount0() {
    final var result = this.springDataDNARepository.retrieveStats();
    Assertions.assertNotNull(result);
    Assertions.assertEquals(0, result.getSimianDNACount());
    Assertions.assertEquals(0, result.getHumanDNACount());
  }

  @Test
  void shouldReturnStatsWithSimianDNACount1AndHumanDNACount0() {

    this.testEntityManager.persist(
        new DNAEntity(
            writeValueAsString(
                new String[] {"ATAAAA", "CACCCC", "TTATCT", "AGACGG", "GCCTCA", "TCACTG"}),
            DNAEntity.DNAType.SIMIAN));

    final var result = this.springDataDNARepository.retrieveStats();

    Assertions.assertNotNull(result);
    Assertions.assertEquals(1, result.getSimianDNACount());
    Assertions.assertEquals(0, result.getHumanDNACount());
  }

  @Test
  void shouldReturnStatsWithSimianDNACount0AndHumanDNACount1() {

    this.testEntityManager.persist(
        new DNAEntity(
            writeValueAsString(
                new String[] {"ATGCGA", "CAGTGC", "TTATCT", "AGACGG", "GCCTCA", "TCACTG"}),
            DNAEntity.DNAType.HUMAN));

    final var result = this.springDataDNARepository.retrieveStats();

    Assertions.assertNotNull(result);
    Assertions.assertEquals(0, result.getSimianDNACount());
    Assertions.assertEquals(1, result.getHumanDNACount());
  }

  @Test
  void shouldReturnStatsWithSimianDNACount1AndHumanDNACount2() {

    this.testEntityManager.persist(
        new DNAEntity(
            writeValueAsString(
                new String[] {"ATAAAA", "CACCCC", "TTATCT", "AGACGG", "GCCTCA", "TCACTG"}),
            DNAEntity.DNAType.SIMIAN));

    this.testEntityManager.persist(
        new DNAEntity(
            writeValueAsString(
                new String[] {"ATGCGA", "CAGTGC", "TTATCT", "AGACGG", "GCCTCA", "TCACTG"}),
            DNAEntity.DNAType.HUMAN));

    this.testEntityManager.persist(
        new DNAEntity(
            writeValueAsString(
                new String[] {"ATGTGA", "ATGTGC", "TTATCT", "AGACGG", "GCATCA", "TCACTG"}),
            DNAEntity.DNAType.HUMAN));

    final var result = this.springDataDNARepository.retrieveStats();

    Assertions.assertNotNull(result);
    Assertions.assertEquals(1, result.getSimianDNACount());
    Assertions.assertEquals(2, result.getHumanDNACount());
  }

  @SneakyThrows
  private String writeValueAsString(Object value) {
    return this.utilsObjectMapper.writeValueAsString(value);
  }
}
