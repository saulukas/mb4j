package org.mb4j.controller.form;

import org.mb4j.controller.url.ControllerUrl4Request;
import org.mb4j.controller.url.Url4Request;

public class FormResponseRedirectToUrlString implements FormResponse {
  public final String urlString;

  private FormResponseRedirectToUrlString(String urlString) {
    this.urlString = urlString;
  }

  public static FormResponseRedirectToUrlString redirectTo(String urlString) {
    return new FormResponseRedirectToUrlString(urlString);
  }

  public static FormResponseRedirectToUrlString redirectTo(ControllerUrl4Request url) {
    return new FormResponseRedirectToUrlString(url.toString());
  }

  public static FormResponseRedirectToUrlString redirectTo(Url4Request url) {
    return new FormResponseRedirectToUrlString(url.toString());
  }
}
