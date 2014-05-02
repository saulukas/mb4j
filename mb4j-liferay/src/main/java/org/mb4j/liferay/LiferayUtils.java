package org.mb4j.liferay;

import com.liferay.portal.util.PortalUtil;
import javax.portlet.PortletRequest;

public class LiferayUtils {
  public static String currentURL(PortletRequest request) {
    return PortalUtil.getCurrentURL(request);
  }
}
