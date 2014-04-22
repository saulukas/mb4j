package org.mb4j.liferay;

import com.google.common.base.Strings;
import com.liferay.portal.util.PortalUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.portlet.PortletRequest;
import org.mb4j.controller.path.UrlPath;
import org.mb4j.controller.path.UrlPathString;
import static org.mb4j.controller.path.UrlPathString.pathStringOf;

public class PortletViewPathUtils {
  public static final String MVC_PATH_PARAM_NAME = "mvcPath";
  public static final int MAX_PATH_SEGMENT_COUNT = 9;
  private static final String VALUE_PREFIX = "viewPath_";

  public static URI currentURI(PortletRequest request) {
    String currentUrl = PortalUtil.getCurrentURL(request);
    try {
      return new URI(currentUrl);
    } catch (URISyntaxException ex) {
      throw new RuntimeException("Invalid current portlet URL (" + currentUrl + "): " + ex, ex);
    }
  }

  public static UrlPath viewPathFrom(PortletRequest request) {
    return viewPathFrom(request.getParameter(MVC_PATH_PARAM_NAME));
  }

  public static UrlPath viewPathFrom(String mvcPath) {
    if (Strings.isNullOrEmpty(mvcPath) || !mvcPath.startsWith(VALUE_PREFIX)) {
      return UrlPath.empty();
    }
    return UrlPathString.urlPath(mvcPath).tail();
  }

  public static String mvcPathParamValueFrom(UrlPath path) {
    List<String> segments = path.segments();
    if (segments.size() > MAX_PATH_SEGMENT_COUNT) {
      throw new RuntimeException("View path [" + pathStringOf(path) + "]"
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
