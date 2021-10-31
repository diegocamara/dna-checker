package com.checker.dna;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class JsonUtils {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  @SneakyThrows
  public static String writeValueAsString(Object value) {
    return objectMapper.writeValueAsString(value);
  }
}
