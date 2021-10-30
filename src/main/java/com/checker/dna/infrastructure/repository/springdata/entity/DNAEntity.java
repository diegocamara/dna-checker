package com.checker.dna.infrastructure.repository.springdata.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class DNAEntity {

  @Id private String id;
  private String segments;
}
