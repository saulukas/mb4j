package org.mb4j.controller;

public interface Page extends Controller {
  @Override
  public PageResponse handle(ViewRequest request);
}
