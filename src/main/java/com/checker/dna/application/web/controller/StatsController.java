package com.checker.dna.application.web.controller;

import com.checker.dna.application.web.model.StatsResponse;
import com.checker.dna.domain.feature.RetrieveDNAStats;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/stats")
public class StatsController {

  private final RetrieveDNAStats retrieveDNAStats;

  @GetMapping
  public ResponseEntity<StatsResponse> stats() {
    final var stats = retrieveDNAStats.handle();
    return ResponseEntity.ok(new StatsResponse(stats));
  }
}
