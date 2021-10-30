package com.checker.dna.domain.feature;

import com.checker.dna.domain.model.DNA;

public interface VerifyDNA {

  DNA handle(String[] segments);
}
