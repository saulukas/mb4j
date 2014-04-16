package org.mb4j.controller;

public interface Page extends View {
  @Override
  public PageResponse handle(ViewRequest request);
}
