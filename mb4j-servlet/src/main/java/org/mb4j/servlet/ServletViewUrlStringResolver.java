package org.mb4j.servlet;

import com.google.common.base.Strings;
import com.google.common.escape.Escaper;
import com.google.common.net.UrlEscapers;
import java.util.Collection;
import org.mb4j.controller.mapping.PathFromViewClassResolver;
import org.mb4j.controller.path.UrlPath;
import org.mb4j.controller.url.ViewUrl;
import org.mb4j.controller.url.ViewUrlStringResolver;
import org.mb4j.controller.NamedParams;

public class ServletViewUrlStringResolver implements ViewUrlStringResolver {
  private final static Escaper PATH_SEGMENT_ESCAPER = UrlEscapers.urlPathSegmentEscaper();
  private final String path2home;
  private final PathFromViewClassResolver pathResolver;

  public ServletViewUrlStringResolver(String path2home, PathFromViewClassResolver pathResolver) {
    this.path2home = path2home;
    this.pathResolver = pathResolver;
  }

  @Override
  public String urlStringOf(ViewUrl url) {
    StringBuilder result = new StringBuilder(path2home);
    appendEscapedPath(result, pathResolver.viewPathFor(url.viewClass));
    if (!url.params.path.isEmpty()) {
      result.append('/');
      appendEscapedPath(result, url.params.path);
    }
    appendNamedParamsString(result, url.params.named);
    String urlString = result.toString();
    if (Strings.isNullOrEmpty(urlString)) {
      urlString = "./";
    }
    return urlString;
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
