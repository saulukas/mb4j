package org.mb4j.controller.url;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import static com.google.common.base.Strings.nullToEmpty;

public class UrlPathString {
  public static final String EMPTY = "";
  public static final String SLASH = "/";
  public static final String ASTERISK = "*";

  public static UrlPath urlPathOf(String pathString) {
    return new UrlPath(Splitter.on(SLASH).omitEmptyStrings().split(nullToEmpty(pathString)));
  }

  public static String pathStringOf(UrlPath urlPath) {
    return Joiner.on(SLASH).join(urlPath.segments());
  }
}
