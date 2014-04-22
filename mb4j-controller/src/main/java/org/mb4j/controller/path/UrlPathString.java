package org.mb4j.controller.path;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import static com.google.common.base.Strings.nullToEmpty;

public class UrlPathString {
  public static final String EMPTY = "";
  public static final String SLASH = "/";
  public static final String ASTERISK = "*";

  public static UrlPath urlPath(String pathString) {
    return new UrlPath(Splitter.on(SLASH).omitEmptyStrings().split(nullToEmpty(pathString)));
  }

  public static String pathStringOf(UrlPath path) {
    return Joiner.on(SLASH).join(path.segments());
  }
}
