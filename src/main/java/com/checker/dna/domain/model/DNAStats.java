package com.checker.dna.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DNAStats {
  private final long humanDNACount;
  private final long simianDNACount;

  public float ratio() {
    return this.humanDNACount > 0 ? (float) simianDNACount / humanDNACount : simianDNACount;
  }
}
