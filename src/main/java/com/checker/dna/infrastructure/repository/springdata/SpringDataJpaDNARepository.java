package com.checker.dna.infrastructure.repository.springdata;

import com.checker.dna.infrastructure.repository.springdata.entity.DNAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaDNARepository extends JpaRepository<DNAEntity, String> {}
