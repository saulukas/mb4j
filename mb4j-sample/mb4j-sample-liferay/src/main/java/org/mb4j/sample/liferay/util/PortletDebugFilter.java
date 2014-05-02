package org.mb4j.sample.liferay.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.filter.ActionFilter;
import javax.portlet.filter.EventFilter;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.RenderFilter;
import javax.portlet.filter.ResourceFilter;
import org.mb4j.liferay.LiferayUtils;
import static org.mb4j.sample.liferay.util.PortletDebugUtils.deltaNanosString;

public class PortletDebugFilter implements RenderFilter, ActionFilter, ResourceFilter, EventFilter {
  @Override
  public void init(FilterConfig filterConfig) throws PortletException {
    System.out.println("==== init " + getClass().getSimpleName());
  }

  @Override
  public void destroy() {
    System.out.println("==== destroy " + getClass().getSimpleName());
  }

  @Override
  public void doFilter(RenderRequest request, RenderResponse response, FilterChain chain) throws IOException, PortletException {
    long startNanos = System.nanoTime();
    System.out.println("-------------------------------------------------------------------");
    String currentUrl = LiferayUtils.currentUrlString(request);
    String path = null;
    String query = null;
    try {
      URI uri = new URI(currentUrl);
      path = uri.getPath();
      query = uri.getQuery();
    } catch (URISyntaxException ex) {
      System.out.println("URI error: " + ex);
    }
    System.out.println("---- doFilter render ... [" + currentUrl + "]"
        + " p=[" + path + "]"
        + " q=[" + query + "]");
    chain.doFilter(request, response);
    System.out.println("---- doFilter render done: " + deltaNanosString(startNanos));
  }

  @Override
  public void doFilter(ActionRequest request, ActionResponse response, FilterChain chain) throws IOException, PortletException {
    long startNanos = System.nanoTime();
    System.out.println("-------------------------------------------------------------------");
    System.out.println("---- doFilter action ...");
    chain.doFilter(request, response);
    System.out.println("---- doFilter action done: " + deltaNanosString(startNanos));
  }

  @Override
  public void doFilter(ResourceRequest request, ResourceResponse response, FilterChain chain) throws IOException, PortletException {
    long startNanos = System.nanoTime();
    System.out.println("-------------------------------------------------------------------");
    System.out.println("---- doFilter resource ...");
    chain.doFilter(request, response);
    System.out.println(PortletDebugUtils.deltaNanosString(startNanos) + "---- doFilter resource done: ");
  }

  @Override
  public void doFilter(EventRequest request, EventResponse response, FilterChain chain) throws IOException, PortletException {
    long startNanos = System.nanoTime();
    System.out.println("---- doFilter event ...");
    chain.doFilter(request, response);
    System.out.println("---- doFilter event done: " + deltaNanosString(startNanos));
  }
}
