package org.mb4j.liferay;

import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.util.PortalUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import static org.mb4j.controller.http.HttpNamedParams.namedParametersFromRawQueryString;
import org.mb4j.controller.url.NamedParams;
import org.mb4j.controller.url.UrlPath;
import org.mb4j.controller.url.UrlPathString;

public class PortletUrlUtils {
  public static UrlPath urlPathFor(PortletRequest request, String friendlyUrlMapping) {
    URI uri = currentURI(request);
    String path = uri.getPath();
    String prefix = "/-/" + friendlyUrlMapping + "/";
    int index = path.indexOf(prefix);
    if (index < 0) {
      return UrlPath.empty();
    }
    String postfix = path.substring(index + prefix.length());
    return UrlPathString.urlPathOf(postfix);
  }

  public static String path2homeFor(PortletResponse response) {
    LiferayPortletResponse liferayResponse = (LiferayPortletResponse) response;
    PortletURL renderURL = liferayResponse.createRenderURL();
    renderURL.setParameter("urlPath", "");
    String path2home = renderURL.toString();
    System.out.println("path2home=[" + path2home + "]");
    return path2home;
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

  public static String currentUrlString(PortletRequest request) {
    return PortalUtil.getCurrentURL(request);
  }

  public static URI currentURI(PortletRequest request) {
    return uriOf(currentUrlString(request));
  }

  public static URI uriOf(String url) {
    try {
      return new URI(url);
    } catch (URISyntaxException ex) {
      throw new RuntimeException("Invalid URL (" + url + "): " + ex, ex);
    }
  }

  public static String authTokenOrNullFrom(PortletResponse response) {
    LiferayPortletResponse liferayResponse = (LiferayPortletResponse) response;
    URI uri = uriOf(liferayResponse.createActionURL().toString());
    NamedParams namedParams = namedParametersFromRawQueryString(uri.getRawQuery());
    return namedParams.valueOrNullOf("p_auth");
  }
}
