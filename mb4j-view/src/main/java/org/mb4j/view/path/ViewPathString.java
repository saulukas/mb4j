package org.mb4j.view.path;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import static com.google.common.base.Strings.nullToEmpty;

public class ViewPathString {
  public static final String EMPTY = "";
  public static final String SLASH = "/";
  public static final String ASTERISK = "*";

  public static ViewPath viewPath(String pathString) {
    return new ViewPath(Splitter.on(SLASH).omitEmptyStrings().split(nullToEmpty(pathString)));
  }

  public static String pathStringOf(ViewPath path) {
    return Joiner.on(SLASH).join(path.segments());
  }
}
