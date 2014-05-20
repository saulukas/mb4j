package org.mb4j.component.sitemap;

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
import org.mb4j.component.view.View;
import org.mb4j.component.url.BufferedUrlPathReader;
import org.mb4j.component.url.UrlPath;
import org.mb4j.component.url.UrlPathString;
import static org.mb4j.component.url.UrlPathString.pathStringOf;
import org.mb4j.component.utils.SimpleClassName;

class SiteMapBuilderNode implements MapUrlPath2Controller {
  @Nullable
  private final SiteMapBuilderNode parent;
  @Nullable
  private final String pathSegment;
  private boolean isAsterisk = false;
  @Nullable
  private View controller = null;
  @Nullable
  private Map<String, SiteMapBuilderNode> children = null;

  private SiteMapBuilderNode(SiteMapBuilderNode parent, String pathSegment) {
    this.parent = parent;
    this.pathSegment = pathSegment;
    if (isRoot() && pathSegment != null) {
      throw new RuntimeException("Root node may not have pathSegment.");
    }
    if (!isRoot() && pathSegment == null) {
      throw new RuntimeException("Non root node must have pathSegment.");
    }
  }

  static SiteMapBuilderNode createRoot() {
    return new SiteMapBuilderNode(null, null);
  }

  @Override
  public Result controllerFor(UrlPath path) {
    return resolve(BufferedUrlPathReader.of(path));
  }

  private Result resolve(BufferedUrlPathReader reader) {
    if (!reader.hasMoreSegments()) {
      return new Result(
          controller,
          reader.processedPath(),
          reader.remainingPath());
    }
    String segment = reader.readSegment();
    SiteMapBuilderNode child = findChildOrNull(segment);
    if (child != null) {
      return child.resolve(reader);
    }
    reader.revertLastRead();
    return new Result(
        (isAsterisk ? controller : null),
        reader.processedPath(),
        reader.remainingPath());
  }

  void mount(UrlPath path, View controller) {
    mount(BufferedUrlPathReader.of(path), controller);
  }

  void mount(BufferedUrlPathReader reader, View controller) {
    if (!reader.hasMoreSegments()) {
      setController(reader, controller);
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
      setController(reader, controller);
      return;
    }
    SiteMapBuilderNode child = findChildOrNull(segment);
    if (child == null) {
      child = new SiteMapBuilderNode(this, segment);
      addChild(child);
    }
    child.mount(reader, controller);
  }

  private void setController(BufferedUrlPathReader reader, View controller) {
    if (hasController()) {
      throw new RuntimeException("Can not mount controller " + debugNameOf(controller)
          + "\n   at path [" + pathStringOf(reader.processedPath()) + "]."
          + "\n   It is already used by " + (isAsterisk ? ".../* " : "") + debugNameOf(this.controller) + ".");
    }
    this.controller = controller;
  }

  private boolean hasController() {
    return controller != null;
  }

  private boolean isRoot() {
    return parent == null;
  }

  private boolean hasChildren() {
    return children != null && !children.isEmpty();
  }

  private String debugNameOf(View controller) {
    return controller == null ? "null" : controller.getClass().getName();
  }

  private void addChild(SiteMapBuilderNode child) {
    nonNullChildren().put(child.pathSegment, child);
  }

  private SiteMapBuilderNode findChildOrNull(String segment) {
    return children == null ? null : children.get(segment);
  }

  private Map<String, SiteMapBuilderNode> nonNullChildren() {
    if (children == null) {
      children = new HashMap<>();
    }
    return children;
  }

  public void collectControllers(Collection<View> result) {
    if (controller != null) {
      result.add(controller);
    }
    if (children != null) {
      for (SiteMapBuilderNode child : children.values()) {
        child.collectControllers(result);
      }
    }
  }

  @Override
  public String toString() {
    return toString("");
  }

  public String toString(String margin) {
    String result = "\"" + Strings.nullToEmpty(pathSegment) + (isAsterisk ? "/*" : "") + "\"";
    if (hasController()) {
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
        SiteMapBuilderNode child = children.get(childName);
        result += "\n" + margin + "|";
        result += "\n" + margin + "+-- "
            + child.toString(margin + (namesIterator.hasNext() ? "|   " : "    "));
      }
    }
    return result;
  }
}
