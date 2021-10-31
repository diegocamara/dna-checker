package com.checker.dna.application.web.controller;

import com.checker.dna.application.web.model.VerifySimianRequest;
import com.checker.dna.extensions.ClearDatabaseExtension;
import com.checker.dna.infrastructure.repository.springdata.entity.DNAEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static com.checker.dna.JsonUtils.writeValueAsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@ExtendWith(ClearDatabaseExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StatsControllerTest {

  @Autowired private MockMvc mockMvc;
  @Autowired private TestEntityManager testEntityManager;

  @Test
  void shouldReturnStatsResponseWithSimianDNACount0AndHumanDNACount0With200StatusCode()
      throws Exception {

    this.mockMvc
        .perform(get("/stats").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpectAll(
            jsonPath("$.count_simian_dna").value(0),
            jsonPath("$.count_human_dna").value(0),
            jsonPath("$.ratio").value(0));
  }

  @Test
  void shouldReturnStatsResponseWithSimianDNACount1AndHumanDNACount2With200StatusCode()
      throws Exception {

    this.testEntityManager.persist(
        new DNAEntity(
            writeValueAsString(
                new String[] {"ATAAAA", "CACCCC", "TTATCT", "AGACGG", "GCCTCA", "TCACTG"}),
            DNAEntity.DNAType.SIMIAN));

    this.testEntityManager.persist(
        new DNAEntity(
            writeValueAsString(
                new String[] {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCCTCA", "TCACTG"}),
            DNAEntity.DNAType.HUMAN));

    this.testEntityManager.persist(
        new DNAEntity(
            writeValueAsString(
                new String[] {"ATGTGA", "ATGTGC", "TTATTT", "AGACGG", "GCATCA", "TCACTG"}),
            DNAEntity.DNAType.HUMAN));

    this.mockMvc
        .perform(get("/stats").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpectAll(
            jsonPath("$.count_simian_dna").value(1),
            jsonPath("$.count_human_dna").value(2),
            jsonPath("$.ratio").value(0.5));
  }

  @Test
  void shouldReturnStatsResponseWithSimianDNACount1AndHumanDNACount0With200StatusCode()
      throws Exception {

    final var simianDNA = new String[] {"ATAAAA", "CACCCC", "TTATCT", "AGACGG", "GCCTCA", "TCACTG"};

    final var simianDNAId = writeValueAsString(simianDNA);

    final var verifySimianRequest1 = new VerifySimianRequest(simianDNA);

    this.mockMvc.perform(
        post("/simian")
            .content(writeValueAsString(verifySimianRequest1))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));

    final var verifySimianRequest2 = new VerifySimianRequest(simianDNA);

    this.mockMvc.perform(
        post("/simian")
            .content(writeValueAsString(verifySimianRequest2))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));

    this.mockMvc
        .perform(get("/stats").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpectAll(
            jsonPath("$.count_simian_dna").value(1),
            jsonPath("$.count_human_dna").value(0),
            jsonPath("$.ratio").value(1));

    final var persistedSimianDNARequest = this.testEntityManager.find(DNAEntity.class, simianDNAId);

    Assertions.assertNotNull(persistedSimianDNARequest);
  }

  @Test
  void shouldReturnStatsResponseWithSimianDNACount2AndHumanDNACount0With200StatusCode()
      throws Exception {

    final var verifySimianRequest1 =
        new VerifySimianRequest(
            new String[] {"ATAAAA", "CACCCC", "TTATCT", "AGACGG", "GCCTCA", "TCACTG"});

    final var verifySimianRequest1Id = writeValueAsString(verifySimianRequest1.getDna());

    this.mockMvc.perform(
        post("/simian")
            .content(writeValueAsString(verifySimianRequest1))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));

    final var verifySimianRequest2 =
        new VerifySimianRequest(
            new String[] {"ATACGA", "CACCCC", "TTATCT", "AGACGG", "GCCTCA", "TCACTG"});

    final var verifySimianRequest2Id = writeValueAsString(verifySimianRequest2.getDna());

    this.mockMvc.perform(
        post("/simian")
            .content(writeValueAsString(verifySimianRequest2))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));

    this.mockMvc
        .perform(get("/stats").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpectAll(
            jsonPath("$.count_simian_dna").value(2),
            jsonPath("$.count_human_dna").value(0),
            jsonPath("$.ratio").value(2));

    final var persistedSimianDNARequest1 =
        this.testEntityManager.find(DNAEntity.class, verifySimianRequest1Id);

    Assertions.assertNotNull(persistedSimianDNARequest1);

    final var persistedSimianDNARequest2 =
        this.testEntityManager.find(DNAEntity.class, verifySimianRequest2Id);

    Assertions.assertNotNull(persistedSimianDNARequest2);
  }
}
