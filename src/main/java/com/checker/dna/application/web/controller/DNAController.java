package com.checker.dna.application.web.controller;

import com.checker.dna.application.web.model.VerifySimianDNAResponse;
import com.checker.dna.application.web.model.VerifySimianRequest;
import com.checker.dna.domain.feature.VerifyDNA;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/simian")
public class DNAController {

  private final VerifyDNA verifyDNA;

  @PostMapping
  public ResponseEntity<VerifySimianDNAResponse> verifySimianDNA(
      @RequestBody VerifySimianRequest verifySimianRequest) {
    return null;
  }
}
