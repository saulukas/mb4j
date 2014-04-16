package org.mb4j.controller.path;

public interface ViewPathReader {
  boolean hasMoreSegments();

  String readSegment();

  ViewPathReader skipSegment();
}
