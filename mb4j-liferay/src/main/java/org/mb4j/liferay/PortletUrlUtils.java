package org.mb4j.liferay;

import com.google.common.base.Strings;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.portlet.PortletRequest;
import org.mb4j.controller.url.NamedParams;
import org.mb4j.controller.url.UrlPath;
import org.mb4j.controller.url.UrlPathString;
import static org.mb4j.controller.url.UrlPathString.pathStringOf;

public class PortletUrlUtils {
  public static final String MVC_PATH_PARAM_NAME = "mvcPath";
  public static final int MAX_PATH_SEGMENT_COUNT = 9;
  private static final String VALUE_PREFIX = "urlPath_"; // same as in nice-urls.xml

  public static UrlPath urlPathFor(PortletRequest request, String friendlyUrlMapping) {
    URI uri = LiferayUtils.currentURI(request);
    String path = uri.getPath();
    String prefix = "/-/" + friendlyUrlMapping + "/";
    int index = path.indexOf(prefix);
    if (index < 0) {
      return UrlPath.empty();
    }
    String postfix = path.substring(index + prefix.length());
    return UrlPathString.urlPathOf(postfix);
  }

  public static UrlPath urlPathFrom(PortletRequest request) {
    return urlPathFrom(request.getParameter(MVC_PATH_PARAM_NAME));
  }

  private static UrlPath urlPathFrom(String mvcPath) {
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

  public static NamedParams namedParamsFrom(PortletRequest request) {
    Map<String, String> name2value = new HashMap<>();
    for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
      String name = entry.getKey();
      String[] values = entry.getValue();
      String value = (values != null && values.length > 0) ? values[0] : null;
      if (value != null) {
        name2value.put(name, value);
      }
    }
    return new NamedParams(name2value);
  }
}
