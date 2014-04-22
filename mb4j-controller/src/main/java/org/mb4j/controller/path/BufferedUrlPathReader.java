package org.mb4j.controller.path;

import com.google.common.collect.Iterators;
import java.util.Iterator;
import java.util.LinkedList;
import static org.mb4j.controller.path.UrlPathString.pathStringOf;

public class BufferedUrlPathReader implements UrlPathReader {
  private final LinkedList<String> processedSegments = new LinkedList<>();
  private final LinkedList<String> remainingSegments = new LinkedList<>();

  private BufferedUrlPathReader(Iterator<String> pathSegments) {
    Iterators.addAll(remainingSegments, pathSegments);
  }

  public static BufferedUrlPathReader bufferedReaderOf(UrlPath path) {
    return new BufferedUrlPathReader(path.segments().iterator());
  }

  public String peakNextSegment() {
    return remainingSegments.getFirst();
  }

  public void revertLastRead() {
    remainingSegments.addFirst(processedSegments.removeLast());
  }

  public UrlPath processedPath() {
    return new UrlPath(processedSegments);
  }

  public UrlPath remainingPath() {
    return new UrlPath(remainingSegments);
  }

  public UrlPath fullPath() {
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
  public UrlPathReader skipSegment() {
    readSegment();
    return this;
  }

  @Override
  public String toString() {
    return pathStringOf(new UrlPath(processedSegments))
        + " | " + pathStringOf(new UrlPath(remainingSegments));
  }
}
