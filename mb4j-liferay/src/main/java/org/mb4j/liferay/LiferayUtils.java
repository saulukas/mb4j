package org.mb4j.liferay;

import com.liferay.portal.util.PortalUtil;
import java.net.URI;
import java.net.URISyntaxException;
import javax.portlet.MimeResponse;
import javax.portlet.PortletRequest;
import static org.mb4j.controller.http.HttpNamedParams.namedParametersFromRawQueryString;
import org.mb4j.controller.url.NamedParams;

public class LiferayUtils {
  public static String currentUrlString(PortletRequest request) {
    return PortalUtil.getCurrentURL(request);
  }

  public static URI currentURI(PortletRequest request) {
    return uriOf(LiferayUtils.currentUrlString(request));
  }

  public static URI uriOf(String url) {
    try {
      return new URI(url);
    } catch (URISyntaxException ex) {
      throw new RuntimeException("Invalid URL (" + url + "): " + ex, ex);
    }
  }

  public static String authTokenOrNullFrom(MimeResponse response) {
    URI uri = uriOf(response.createActionURL().toString());
    NamedParams namedParams = namedParametersFromRawQueryString(uri.getRawQuery());
    return namedParams.valueOrNullOf("p_auth");
  }
}
