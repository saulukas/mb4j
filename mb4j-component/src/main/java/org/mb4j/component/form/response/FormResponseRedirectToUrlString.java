package org.mb4j.component.form.response;

import org.mb4j.component.view.ViewUrl4Response;
import org.mb4j.component.asset.AssetUrl4Response;

public class FormResponseRedirectToUrlString implements FormResponse {
  public final String urlString;

  private FormResponseRedirectToUrlString(String urlString) {
    this.urlString = urlString;
  }

  public static FormResponseRedirectToUrlString redirectTo(String urlString) {
    return new FormResponseRedirectToUrlString(urlString);
  }

  public static FormResponseRedirectToUrlString redirectTo(ViewUrl4Response url) {
    return new FormResponseRedirectToUrlString(url.toString());
  }

  public static FormResponseRedirectToUrlString redirectTo(AssetUrl4Response url) {
    return new FormResponseRedirectToUrlString(url.toString());
  }
}
