package org.mb4j.controller.url;

public interface UrlPathReader {
  boolean hasMoreSegments();

  String readSegment();

  UrlPathReader skipSegment();
}
