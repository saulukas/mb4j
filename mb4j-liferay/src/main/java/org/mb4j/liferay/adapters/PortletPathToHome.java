package org.mb4j.liferay.adapters;

import javax.portlet.PortletRequest;
import org.mb4j.component.url.UrlPathStringToHome;

public class PortletPathToHome {

    public static String pathToAssets(PortletRequest request, String pathString) {
        String contextPath = request.getContextPath();
        if (contextPath.startsWith("/")) {
            contextPath = contextPath.substring(1);
        }
        return UrlPathStringToHome.from(pathString) + contextPath;
    }
}
