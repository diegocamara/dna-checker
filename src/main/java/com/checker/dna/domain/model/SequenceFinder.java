package com.checker.dna.domain.model;

import java.util.List;

public interface SequenceFinder {

  List<Sequence> find(char[][] segments);
}
