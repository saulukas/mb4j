package org.mb4j.brick.internal;

import java.io.Reader;

public class Parser {
  private final Reader reader;
  private final Class brickClass;

  public Parser(Reader reader, Class brickClass) {
    this.reader = reader;
    this.brickClass = brickClass;
  }
}
