package org.mb4j.controller.path;

public interface UrlPathReader {
  boolean hasMoreSegments();

  String readSegment();

  UrlPathReader skipSegment();
}
