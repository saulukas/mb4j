package org.mb4j.view.path;

public interface ViewPathReader {
  boolean hasMoreSegments();

  String readSegment();

  ViewPathReader skipSegment();
}
