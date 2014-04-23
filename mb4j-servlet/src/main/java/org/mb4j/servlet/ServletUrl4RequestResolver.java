package org.mb4j.servlet;

import org.mb4j.controller.url.Url4Request;
import org.mb4j.controller.url.Url4RequestResolver;

public class ServletUrl4RequestResolver implements Url4RequestResolver {
  public final String path2home;

  public ServletUrl4RequestResolver(String path2home) {
    this.path2home = path2home;
  }

  @Override
  public Url4Request resolveUrl(String urlFromHome) {
    return new Url4Request(path2home + urlFromHome);
  }
}
