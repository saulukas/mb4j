package org.mb4j.component.url;

public interface UrlPathReader {

    boolean hasMoreSegments();

    String readSegment();

    UrlPathReader skipSegment();
}
