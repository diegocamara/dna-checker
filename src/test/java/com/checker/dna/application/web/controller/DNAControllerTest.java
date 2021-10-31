package com.checker.dna.application.web.controller;

import com.checker.dna.application.web.model.VerifySimianRequest;
import com.checker.dna.extensions.ClearDatabaseExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.checker.dna.JsonUtils.writeValueAsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(ClearDatabaseExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DNAControllerTest {

  @Autowired private MockMvc mockMvc;

  @Test
  void shouldReturnErrorMessageResponseWith400StatusCode() throws Exception {

    final var simianDNA = new String[] {"CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCTA"};
    final var dnaRequest = new VerifySimianRequest(simianDNA);
    this.mockMvc
        .perform(
            post("/simian")
                .content(writeValueAsString(dnaRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").exists());
  }

  @Test
  void shouldReturnVerifySimianResponseTrueWith200StatusCode() throws Exception {
    final var simianDNA = new String[] {"CTGAGA", "CTATGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG"};
    final var dnaRequest = new VerifySimianRequest(simianDNA);
    this.mockMvc
        .perform(
            post("/simian")
                .content(writeValueAsString(dnaRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.is_simian").value(true));
  }

  @Test
  void shouldReturnVerifySimianResponseFalseWith200StatusCode() throws Exception {
    final var humanDNA = new String[] {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCCTCA", "TCACTG"};
    final var dnaRequest = new VerifySimianRequest(humanDNA);
    this.mockMvc
        .perform(
            post("/simian")
                .content(writeValueAsString(dnaRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.is_simian").value(false));
  }
}
