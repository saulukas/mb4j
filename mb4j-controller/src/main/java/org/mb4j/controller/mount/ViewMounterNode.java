package org.mb4j.controller.mount;

import org.mb4j.controller.reflection.SimpleClassName;
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import static com.google.common.collect.Lists.newArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import org.mb4j.controller.Controller;
import org.mb4j.controller.path.BufferedUrlPathReader;
import static org.mb4j.controller.path.BufferedUrlPathReader.bufferedReaderOf;
import org.mb4j.controller.path.UrlPath;
import org.mb4j.controller.path.UrlPathString;
import static org.mb4j.controller.path.UrlPathString.pathStringOf;

class ViewMounterNode implements ViewFromPathResolver {
  @Nullable
  private final ViewMounterNode parent;
  @Nullable
  private final String pathSegment;
  private boolean isAsterisk = false;
  @Nullable
  private Controller view = null;
  @Nullable
  private Map<String, ViewMounterNode> children = null;

  private ViewMounterNode(ViewMounterNode parent, String pathSegment) {
    this.parent = parent;
    this.pathSegment = pathSegment;
    if (isRoot() && pathSegment != null) {
      throw new RuntimeException("Root node may not have pathSegment.");
    }
    if (!isRoot() && pathSegment == null) {
      throw new RuntimeException("Non root node must have pathSegment.");
    }
  }

  static ViewMounterNode createRoot() {
    return new ViewMounterNode(null, null);
  }

  @Override
  public Result resolve(UrlPath path) {
    return resolve(bufferedReaderOf(path));
  }

  private Result resolve(BufferedUrlPathReader reader) {
    if (!reader.hasMoreSegments()) {
      return new Result(
          view,
          reader.processedPath(),
          reader.remainingPath());
    }
    String segment = reader.readSegment();
    ViewMounterNode child = findChildOrNull(segment);
    if (child != null) {
      return child.resolve(reader);
    }
    reader.revertLastRead();
    return new Result(
        (isAsterisk ? view : null),
        reader.processedPath(),
        reader.remainingPath());
  }

  void mount(UrlPath path, Controller view) {
    mount(bufferedReaderOf(path), view);
  }

  void mount(BufferedUrlPathReader reader, Controller view) {
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
    ViewMounterNode child = findChildOrNull(segment);
    if (child == null) {
      child = new ViewMounterNode(this, segment);
      addChild(child);
    }
    child.mount(reader, view);
  }

  private void setView(BufferedUrlPathReader reader, Controller view) {
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

  private String debugNameOf(Controller view) {
    return view == null ? "null" : view.getClass().getName();
  }

  private void addChild(ViewMounterNode child) {
    nonNullChildren().put(child.pathSegment, child);
  }

  private ViewMounterNode findChildOrNull(String segment) {
    return children == null ? null : children.get(segment);
  }

  private Map<String, ViewMounterNode> nonNullChildren() {
    if (children == null) {
      children = new HashMap<String, ViewMounterNode>();
    }
    return children;
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
        ViewMounterNode child = children.get(childName);
        result += "\n" + margin + "|";
        result += "\n" + margin + "+-- "
            + child.toString(margin + (namesIterator.hasNext() ? "|   " : "    "));
      }
    }
    return result;
  }
}
