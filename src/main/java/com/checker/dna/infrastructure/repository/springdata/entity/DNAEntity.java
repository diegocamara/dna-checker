package com.checker.dna.infrastructure.repository.springdata.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "DNA_TB")
@NoArgsConstructor
@AllArgsConstructor
public class DNAEntity {

  public enum DNAType {
    HUMAN,
    SIMIAN
  };

  @Id private String id;

  @Enumerated(EnumType.STRING)
  private DNAType type;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    DNAEntity dnaEntity = (DNAEntity) o;
    return id != null && Objects.equals(id, dnaEntity.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
