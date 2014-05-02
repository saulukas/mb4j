package org.mb4j.liferay;

import com.google.common.base.Strings;
import java.util.List;
import javax.portlet.PortletRequest;
import org.mb4j.controller.url.UrlPath;
import org.mb4j.controller.url.UrlPathString;
import static org.mb4j.controller.url.UrlPathString.pathStringOf;

public class PortletUrlPathUtils {
  public static final String MVC_PATH_PARAM_NAME = "mvcPath";
  public static final int MAX_PATH_SEGMENT_COUNT = 9;
  private static final String VALUE_PREFIX = "urlPath_"; // same as in nice-urls.xml

  public static UrlPath urlPathFrom(PortletRequest request) {
    return urlPathFrom(request.getParameter(MVC_PATH_PARAM_NAME));
  }

  public static UrlPath urlPathFrom(String mvcPath) {
    if (Strings.isNullOrEmpty(mvcPath) || !mvcPath.startsWith(VALUE_PREFIX)) {
      return UrlPath.empty();
    }
    return UrlPathString.urlPathOf(mvcPath).tail();
  }

  public static String mvcPathParamValueFrom(UrlPath path) {
    List<String> segments = path.segments();
    if (segments.size() > MAX_PATH_SEGMENT_COUNT) {
      throw new RuntimeException("URL path [" + pathStringOf(path) + "]"
          + " contains too many segments (" + segments.size() + ")."
          + " Max segment count is " + MAX_PATH_SEGMENT_COUNT + ".");
    }
    String result = VALUE_PREFIX + segments.size();
    if (segments.size() > 0) {
      result += "/" + pathStringOf(path);
    }
    return result;
  }
}
