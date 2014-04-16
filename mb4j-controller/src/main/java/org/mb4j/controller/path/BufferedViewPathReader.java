package org.mb4j.controller.path;

import com.google.common.collect.Iterators;
import java.util.Iterator;
import java.util.LinkedList;
import static org.mb4j.controller.path.ViewPathString.pathStringOf;

public class BufferedViewPathReader implements ViewPathReader {
  private final LinkedList<String> processedSegments = new LinkedList<String>();
  private final LinkedList<String> remainingSegments = new LinkedList<String>();

  private BufferedViewPathReader(Iterator<String> pathSegments) {
    Iterators.addAll(remainingSegments, pathSegments);
  }

  public static BufferedViewPathReader bufferedReaderOf(ViewPath path) {
    return new BufferedViewPathReader(path.segments().iterator());
  }

  public String peakNextSegment() {
    return remainingSegments.getFirst();
  }

  public void revertLastRead() {
    remainingSegments.addFirst(processedSegments.removeLast());
  }

  public ViewPath processedPath() {
    return new ViewPath(processedSegments);
  }

  public ViewPath remainingPath() {
    return new ViewPath(remainingSegments);
  }

  public ViewPath fullPath() {
    return processedPath().add(remainingPath());
  }

  @Override
  public boolean hasMoreSegments() {
    return !remainingSegments.isEmpty();
  }

  @Override
  public String readSegment() {
    processedSegments.add(remainingSegments.removeFirst());
    return processedSegments.getLast();
  }

  @Override
  public ViewPathReader skipSegment() {
    readSegment();
    return this;
  }

  @Override
  public String toString() {
    return pathStringOf(new ViewPath(processedSegments))
        + " | " + pathStringOf(new ViewPath(remainingSegments));
  }
}
