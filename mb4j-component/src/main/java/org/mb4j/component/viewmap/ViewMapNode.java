package org.mb4j.component.viewmap;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import static com.google.common.collect.Lists.newArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import org.mb4j.component.View;
import org.mb4j.component.url.BufferedUrlPathReader;
import org.mb4j.component.url.UrlPath;
import org.mb4j.component.url.UrlPathString;
import static org.mb4j.component.url.UrlPathString.pathStringOf;
import org.mb4j.component.utils.SimpleClassName;

class ViewMapNode implements MapUrlPath2View {

    @Nullable
    private final ViewMapNode parent;
    @Nullable
    private final String pathSegment;
    private boolean isAsterisk = false;
    @Nullable
    private View view = null;
    @Nullable
    private Map<String, ViewMapNode> children = null;

    private ViewMapNode(ViewMapNode parent, String pathSegment) {
        this.parent = parent;
        this.pathSegment = pathSegment;
        if (isRoot() && pathSegment != null) {
            throw new RuntimeException("Root node may not have pathSegment.");
        }
        if (!isRoot() && pathSegment == null) {
            throw new RuntimeException("Non root node must have pathSegment.");
        }
    }

    static ViewMapNode createRoot() {
        return new ViewMapNode(null, null);
    }

    @Override
    public Result viewAt(UrlPath path) {
        return resolve(BufferedUrlPathReader.of(path));
    }

    private Result resolve(BufferedUrlPathReader reader) {
        if (!reader.hasMoreSegments()) {
            return new Result(
                    view,
                    reader.processedPath(),
                    reader.remainingPath());
        }
        String segment = reader.readSegment();
        ViewMapNode child = findChildOrNull(segment);
        if (child != null) {
            return child.resolve(reader);
        }
        reader.revertLastRead();
        return new Result(
                (isAsterisk ? view : null),
                reader.processedPath(),
                reader.remainingPath());
    }

    void mount(UrlPath path, View view) {
        mount(BufferedUrlPathReader.of(path), view);
    }

    void mount(BufferedUrlPathReader reader, View view) {
        if (!reader.hasMoreSegments()) {
            setView(reader, view);
            return;
        }
        String segment = reader.readSegment();
        if (Objects.equal(segment, UrlPathString.ASTERISK)) {
            if (reader.hasMoreSegments()) {
                throw new RuntimeException("Asterisk '" + UrlPathString.ASTERISK + "'"
                        + " is only allowed at the path end: \"" + pathStringOf(reader.fullPath()) + "\"");
            }
            reader.revertLastRead();
            isAsterisk = true;
            setView(reader, view);
            return;
        }
        ViewMapNode child = findChildOrNull(segment);
        if (child == null) {
            child = new ViewMapNode(this, segment);
            addChild(child);
        }
        child.mount(reader, view);
    }

    private void setView(BufferedUrlPathReader reader, View view) {
        if (hasView()) {
            throw new RuntimeException("Can not mount view " + debugNameOf(view)
                    + "\n   at path [" + pathStringOf(reader.processedPath()) + "]."
                    + "\n   It is already used by " + (isAsterisk ? ".../* " : "") + debugNameOf(this.view) + ".");
        }
        this.view = view;
    }

    private boolean hasView() {
        return view != null;
    }

    private boolean isRoot() {
        return parent == null;
    }

    private boolean hasChildren() {
        return children != null && !children.isEmpty();
    }

    private String debugNameOf(View view) {
        return view == null ? "null" : view.getClass().getName();
    }

    private void addChild(ViewMapNode child) {
        nonNullChildren().put(child.pathSegment, child);
    }

    private ViewMapNode findChildOrNull(String segment) {
        return children == null ? null : children.get(segment);
    }

    private Map<String, ViewMapNode> nonNullChildren() {
        if (children == null) {
            children = new HashMap<>();
        }
        return children;
    }

    public void collectViews(Collection<View> result) {
        if (view != null) {
            result.add(view);
        }
        if (children != null) {
            for (ViewMapNode child : children.values()) {
                child.collectViews(result);
            }
        }
    }

    @Override
    public String toString() {
        return toString("");
    }

    public String toString(String margin) {
        String result = "\"" + Strings.nullToEmpty(pathSegment) + (isAsterisk ? "/*" : "") + "\"";
        if (hasView()) {
            result += "   ....";
            result = Strings.padEnd(result, 62 - margin.length(), '.');
            result += " " + SimpleClassName.of(view.getClass());
        }
        if (hasChildren()) {
            List<String> childNames = newArrayList(children.keySet());
            Collections.sort(childNames);
            Iterator<String> namesIterator = childNames.iterator();
            while (namesIterator.hasNext()) {
                String childName = namesIterator.next();
                ViewMapNode child = children.get(childName);
                result += "\n" + margin + "|";
                result += "\n" + margin + "+-- "
                        + child.toString(margin + (namesIterator.hasNext() ? "|   " : "    "));
            }
        }
        return result;
    }
}
