package com.checker.dna.application.web.model;

import com.checker.dna.domain.model.DNAStats;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatsResponse {

  @JsonProperty("count_simian_dna")
  private long simianDNACount;

  @JsonProperty("count_human_dna")
  private long humanDNACount;

  private float ratio;

  public StatsResponse(DNAStats dnaStats) {
    this.simianDNACount = dnaStats.getSimianDNACount();
    this.humanDNACount = dnaStats.getHumanDNACount();
    this.ratio = dnaStats.ratio();
  }
}
