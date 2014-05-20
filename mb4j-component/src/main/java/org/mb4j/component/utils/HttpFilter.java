package org.mb4j.component.utils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class HttpFilter implements Filter {
  protected abstract void filter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
      throws IOException, ServletException;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    filter((HttpServletRequest) request, (HttpServletResponse) response, chain);
  }

  @Override
  public void destroy() {
  }
}
