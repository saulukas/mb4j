package org.mb4j.view;

public interface Page extends View {
  @Override
  public PageResponse handle(ViewRequest request);
}
