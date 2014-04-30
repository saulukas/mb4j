package org.mb4j.controller.form;

import org.mb4j.controller.url.ControllerUrl4Request;
import org.mb4j.controller.url.Url4Request;

public class FormResponseRedirect implements FormResponse {
  public final String urlString;

  public FormResponseRedirect(String urlString) {
    this.urlString = urlString;
  }

  public static FormResponseRedirect redirectTo(String urlString) {
    return new FormResponseRedirect(urlString);
  }

  public static FormResponseRedirect redirectTo(ControllerUrl4Request url) {
    return new FormResponseRedirect(url.toString());
  }

  public static FormResponseRedirect redirectTo(Url4Request url) {
    return new FormResponseRedirect(url.toString());
  }
}
