package org.mb4j.controller.url;

import static com.google.common.collect.Lists.newArrayList;
import java.util.Collections;
import static java.util.Collections.unmodifiableList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class UrlPath {
  private static final UrlPath EMPTY = new UrlPath(Collections.EMPTY_LIST);
  private final List<String> segments;

  UrlPath(Iterable<String> segments) {
    this(segments.iterator());
  }

  UrlPath(Iterator<String> segments) {
    this.segments = unmodifiableList(newArrayList(segments));
  }

  public static UrlPath empty() {
    return EMPTY;
  }

  public boolean isEmpty() {
    return segments.isEmpty();
  }

  public List<String> segments() {
    return segments;
  }

  public UrlPath add(UrlPath path) {
    List<String> resultSegments = new LinkedList<>();
    resultSegments.addAll(segments);
    resultSegments.addAll(path.segments);
    return new UrlPath(resultSegments);
  }

  public UrlPath tail() {
    Iterator<String> tail = segments.iterator();
    if (tail.hasNext()) {
      tail.next();
    }
    return new UrlPath(tail);
  }

  @Override
  public String toString() {
    return UrlPathString.pathStringOf(this);
  }
}
