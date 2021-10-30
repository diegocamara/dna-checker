package com.checker.dna.application.web.controller;

import com.checker.dna.domain.feature.RetrieveDNAStats;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/stats")
public class StatsController {

  private final RetrieveDNAStats retrieveDNAStats;
}
