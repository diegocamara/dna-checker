package com.checker.dna.domain.feature;

import com.checker.dna.domain.model.DNA;
import com.checker.dna.domain.model.DNARepository;
import lombok.AllArgsConstructor;

import javax.inject.Named;

import static com.checker.dna.domain.model.DNAParser.parser;

@Named
@AllArgsConstructor
public class VerifyDNAImpl implements VerifyDNA {

  private final DNARepository dnaRepository;

  @Override
  public DNA handle(String[] segments) {
    final var dnaResult = parser().withSegments(segments).parse();
    this.dnaRepository.storeIfNotExists(dnaResult);
    return dnaResult;
  }
}
