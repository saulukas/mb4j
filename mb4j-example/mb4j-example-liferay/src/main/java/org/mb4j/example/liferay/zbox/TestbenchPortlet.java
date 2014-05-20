package org.mb4j.example.liferay.zbox;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import static javax.portlet.PortletSession.APPLICATION_SCOPE;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;
import org.mb4j.example.liferay.util.PortletDebugUtils;

public class TestbenchPortlet extends GenericPortlet {
  AtomicLong renderCount = new AtomicLong();

  @Override
  protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
    System.out.println("mmmmm: " + PortletDebugUtils.printParametersOf("render", this, request));
    response.getWriter().println(""
        + "\nTetbench...");
    //
    //   nice URLs
    //
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
    ResourceURL resourceURL = response.createResourceURL();
    resourceURL.setResourceID("myResourceID");
    response.getWriter().println(""
        + "<p>"
        + "    <a href=\"" + resourceURL + "\">Resource URL</a>"
        + "</p>");
    //
    //   session attributes
    //
    boolean hadSession = request.getPortletSession(false) != null;
    Object oldValue = request.getPortletSession().getAttribute("LIFERAY_SHARED_A123", APPLICATION_SCOPE);
    String newValue = "mb" + renderCount.incrementAndGet();
    request.getPortletSession().setAttribute("LIFERAY_SHARED_A123", newValue, APPLICATION_SCOPE);
    response.getWriter().println(""
        + "<p>Session existed: " + hadSession + " oldValue=" + oldValue + " newValue=" + newValue + "</p>");
    response.getWriter().println(""
        + "<pre>Application attributes: "
        + separateWith("\n", request.getPortletSession().getAttributeMap(APPLICATION_SCOPE))
        + "</pre>"
        + "<pre>Portlet attributes: "
        + separateWith("\n", request.getPortletSession().getAttributeMap())
        + "</pre>"
    );
  }

  @Override
  public void serveResource(ResourceRequest request, ResourceResponse response) throws PortletException, IOException {
    System.out.println("Resource request arrived...");
    response.getWriter().write("resourceId=" + request.getResourceID());
    super.serveResource(request, response);
  }

  private String separateWith(String separator, Map<String, Object> attributeMap) {
    String result = "";
    for (Map.Entry<String, Object> entry : attributeMap.entrySet()) {
      result += separator + entry.getKey() + "=[" + entry.getValue() + "]";
    }
    return result;
  }
}
