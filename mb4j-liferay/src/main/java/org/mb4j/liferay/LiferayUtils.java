package org.mb4j.liferay;

import com.liferay.portal.util.PortalUtil;
import java.net.URI;
import java.net.URISyntaxException;
import javax.portlet.PortletRequest;

public class LiferayUtils {
  public static String currentUrlString(PortletRequest request) {
    return PortalUtil.getCurrentURL(request);
  }

  public static URI currentURI(PortletRequest request) {
    String currentUrl = LiferayUtils.currentUrlString(request);
    try {
      return new URI(currentUrl);
    } catch (URISyntaxException ex) {
      throw new RuntimeException("Invalid current portlet URL (" + currentUrl + "): " + ex, ex);
    }
  }
}
