package org.mb4j.liferay;

import javax.portlet.PortletRequest;
import org.mb4j.controller.http.UrlPathStringToHome;

public class PortletPathToHome {
  public static String pathStringToHomeFrom(PortletRequest request, String pathString) {
    String contextPath = request.getContextPath();
    if (contextPath.startsWith("/")) {
      contextPath = contextPath.substring(1);
    }
    return UrlPathStringToHome.from(pathString) + contextPath;
  }
}
