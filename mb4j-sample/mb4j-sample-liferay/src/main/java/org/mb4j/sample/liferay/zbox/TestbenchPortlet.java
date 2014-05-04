package org.mb4j.sample.liferay.zbox;

import java.io.IOException;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.mb4j.sample.liferay.util.PortletDebugUtils;

public class TestbenchPortlet extends GenericPortlet {
  @Override
  protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
    System.out.println("mmmmm: " + PortletDebugUtils.printParametersOf("render", this, request));
    response.getWriter().println(""
        + "\nTetbench...");
    PortletURL url1 = response.createRenderURL();
    url1.setParameter("mvcPath", "urlPath_2/your/view");
    response.getWriter().println(""
        + "<p>"
        + "    <a href=\"" + url1 + "\">URL to your view</a>"
        + "</p>");
    PortletURL url2 = response.createRenderURL();
    url2.setParameter("mvcPath", "urlPath_0");
    response.getWriter().println(""
        + "<p>"
        + "    <a href=\"" + url2 + "\">URL to home</a>"
        + "</p>");
    PortletURL url3 = response.createRenderURL();
    url3.setParameter("seg0", "a");
    url3.setParameter("seg1", "b");
    url3.setParameter("segZ", "c/d/e/f/g/h/i/j/k/l/m/n/o/p");
    url3.setParameter("mvcPath", "urlPath_2/a/b/c/d/e/f/g/h/i/j/k/l/m/n/o/p");
    response.getWriter().println(""
        + "<p>"
        + "    <a href=\"" + url3 + "\">Long URL</a>"
        + "</p>");
  }
}
