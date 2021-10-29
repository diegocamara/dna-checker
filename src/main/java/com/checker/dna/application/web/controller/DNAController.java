package com.checker.dna.application.web.controller;

import com.checker.dna.application.web.model.VerifySimianDNAResponse;
import com.checker.dna.application.web.model.VerifySimianRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simian")
public class DNAController {

  @PostMapping
  public ResponseEntity<VerifySimianDNAResponse> verifySimianDNA(
      @RequestBody VerifySimianRequest verifySimianRequest) {
    return null;
  }
}
