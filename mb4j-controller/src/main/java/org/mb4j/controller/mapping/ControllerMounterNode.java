package org.mb4j.controller.mapping;

import org.mb4j.controller.utils.SimpleClassName;
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
import org.mb4j.controller.url.BufferedUrlPathReader;
import static org.mb4j.controller.url.BufferedUrlPathReader.bufferedReaderOf;
import org.mb4j.controller.url.UrlPath;
import org.mb4j.controller.url.UrlPathString;
import static org.mb4j.controller.url.UrlPathString.pathStringOf;

class ControllerMounterNode implements UrlPath2ControllerResolver {
  @Nullable
  private final ControllerMounterNode parent;
  @Nullable
  private final String pathSegment;
  private boolean isAsterisk = false;
  @Nullable
  private Controller controller = null;
  @Nullable
  private Map<String, ControllerMounterNode> children = null;

  private ControllerMounterNode(ControllerMounterNode parent, String pathSegment) {
    this.parent = parent;
    this.pathSegment = pathSegment;
    if (isRoot() && pathSegment != null) {
      throw new RuntimeException("Root node may not have pathSegment.");
    }
    if (!isRoot() && pathSegment == null) {
      throw new RuntimeException("Non root node must have pathSegment.");
    }
  }

  static ControllerMounterNode createRoot() {
    return new ControllerMounterNode(null, null);
  }

  @Override
  public Result resolve(UrlPath path) {
    return resolve(bufferedReaderOf(path));
  }

  private Result resolve(BufferedUrlPathReader reader) {
    if (!reader.hasMoreSegments()) {
      return new Result(
          controller,
          reader.processedPath(),
          reader.remainingPath());
    }
    String segment = reader.readSegment();
    ControllerMounterNode child = findChildOrNull(segment);
    if (child != null) {
      return child.resolve(reader);
    }
    reader.revertLastRead();
    return new Result(
        (isAsterisk ? controller : null),
        reader.processedPath(),
        reader.remainingPath());
  }

  void mount(UrlPath path, Controller view) {
    mount(bufferedReaderOf(path), view);
  }

  void mount(BufferedUrlPathReader reader, Controller view) {
    if (!reader.hasMoreSegments()) {
      setController(reader, view);
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
      setController(reader, view);
      return;
    }
    ControllerMounterNode child = findChildOrNull(segment);
    if (child == null) {
      child = new ControllerMounterNode(this, segment);
      addChild(child);
    }
    child.mount(reader, view);
  }

  private void setController(BufferedUrlPathReader reader, Controller view) {
    if (hasView()) {
      throw new RuntimeException("Can not mount view " + debugNameOf(view)
          + "\n   at path [" + pathStringOf(reader.processedPath()) + "]."
          + "\n   It is already used by " + (isAsterisk ? ".../* " : "") + debugNameOf(this.controller) + ".");
    }
    this.controller = view;
  }

  private boolean hasView() {
    return controller != null;
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

  private void addChild(ControllerMounterNode child) {
    nonNullChildren().put(child.pathSegment, child);
  }

  private ControllerMounterNode findChildOrNull(String segment) {
    return children == null ? null : children.get(segment);
  }

  private Map<String, ControllerMounterNode> nonNullChildren() {
    if (children == null) {
      children = new HashMap<>();
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
      result += " " + SimpleClassName.of(controller.getClass());
    }
    if (hasChildren()) {
      List<String> childNames = newArrayList(children.keySet());
      Collections.sort(childNames);
      Iterator<String> namesIterator = childNames.iterator();
      while (namesIterator.hasNext()) {
        String childName = namesIterator.next();
        ControllerMounterNode child = children.get(childName);
        result += "\n" + margin + "|";
        result += "\n" + margin + "+-- "
            + child.toString(margin + (namesIterator.hasNext() ? "|   " : "    "));
      }
    }
    return result;
  }
}
