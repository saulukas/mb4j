package org.mb4j.view.path;

import static com.google.common.collect.Lists.newArrayList;
import java.util.Collections;
import static java.util.Collections.unmodifiableList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ViewPath {
    private static final ViewPath EMPTY = new ViewPath(Collections.EMPTY_LIST);
    private final List<String> segments;

    ViewPath(Iterable<String> segments) {
        this(segments.iterator());
    }

    ViewPath(Iterator<String> segments) {
        this.segments = unmodifiableList(newArrayList(segments));
    }

    public static ViewPath empty() {
        return EMPTY;
    }

    public boolean isEmpty() {
        return segments.isEmpty();
    }

    public List<String> segments() {
        return segments;
    }

    public ViewPath add(ViewPath path) {
        List<String> resultSegments = new LinkedList<String>();
        resultSegments.addAll(segments);
        resultSegments.addAll(path.segments);
        return new ViewPath(resultSegments);
    }

    public ViewPath tail() {
        Iterator<String> tail = segments.iterator();
        if (tail.hasNext()) {
            tail.next();
        }
        return new ViewPath(tail);
    }

    @Override
    public String toString() {
        return ViewPathString.pathStringOf(this);
    }
}
