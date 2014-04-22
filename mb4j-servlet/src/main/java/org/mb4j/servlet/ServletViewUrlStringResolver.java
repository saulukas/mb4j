package org.mb4j.servlet;

import com.google.common.base.Strings;
import com.google.common.escape.Escaper;
import com.google.common.net.UrlEscapers;
import java.util.Collection;
import org.mb4j.controller.mapping.ControllerClass2UrlPathResolver;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.ControllerUrl4Request;
import org.mb4j.controller.url.ControllerUrl4RequestResolver;
import org.mb4j.controller.url.NamedParams;
import org.mb4j.controller.url.UrlPath;

public class ServletViewUrlStringResolver implements ControllerUrl4RequestResolver {
  private final static Escaper PATH_SEGMENT_ESCAPER = UrlEscapers.urlPathSegmentEscaper();
  private final String path2home;
  private final ControllerClass2UrlPathResolver pathResolver;

  public ServletViewUrlStringResolver(String path2home, ControllerClass2UrlPathResolver pathResolver) {
    this.path2home = path2home;
    this.pathResolver = pathResolver;
  }

  @Override
  public ControllerUrl4Request resolve(ControllerUrl url) {
    StringBuilder result = new StringBuilder(path2home);
    appendEscapedPath(result, pathResolver.urlPathFor(url.controllerClass));
    if (!url.params.path.isEmpty()) {
      result.append('/');
      appendEscapedPath(result, url.params.path);
    }
    appendNamedParamsString(result, url.params.named);
    String urlString = result.toString();
    if (Strings.isNullOrEmpty(urlString)) {
      urlString = "./";
    }
    return new ControllerUrl4Request(urlString);
  }

  private void appendNamedParamsString(StringBuilder result, NamedParams params) {
    boolean isFirst = true;
    Collection<String> names = params.names();
    for (String name : names) {
      result.append(isFirst ? '?' : '&');
      appendEscapedName(result, name);
      String value = params.valueOf(name);
      if (value != null) {
        result.append('=');
        appendEscapedValue(result, value);
      }
      isFirst = false;
    }
  }

  static void appendEscapedPath(StringBuilder result, UrlPath path) {
    boolean isFirst = true;
    for (String segment : path.segments()) {
      if (!isFirst) {
        result.append('/');
      }
      appendEscapedPathSegment(result, segment);
      isFirst = false;
    }
  }

  static void appendEscapedPathSegment(StringBuilder result, String segment) {
    result.append(PATH_SEGMENT_ESCAPER.escape(segment));
  }

  static void appendEscapedName(StringBuilder result, String name) {
    result.append(PATH_SEGMENT_ESCAPER.escape(name));
  }

  static void appendEscapedValue(StringBuilder result, String value) {
    result.append(PATH_SEGMENT_ESCAPER.escape(value));
  }
}
