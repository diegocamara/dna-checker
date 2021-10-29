package com.checker.dna.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Occurrence {
  private final int posX;
  private final int posY;
  private final char value;
}
