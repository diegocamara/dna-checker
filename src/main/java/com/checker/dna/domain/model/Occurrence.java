package com.checker.dna.domain.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class Occurrence {
  private final int rowIndex;
  private final int columnIndex;
  private final char value;
}
