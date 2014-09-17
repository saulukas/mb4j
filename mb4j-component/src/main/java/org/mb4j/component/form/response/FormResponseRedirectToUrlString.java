package org.mb4j.component.form.response;

import org.mb4j.component.ViewUrl;
import org.mb4j.component.AssetUrl;

public class FormResponseRedirectToUrlString implements FormResponse {

    public final String urlString;

    private FormResponseRedirectToUrlString(String urlString) {
        this.urlString = urlString;
    }

    public static FormResponseRedirectToUrlString redirectTo(String urlString) {
        return new FormResponseRedirectToUrlString(urlString);
    }

    public static FormResponseRedirectToUrlString redirectTo(ViewUrl url) {
        return new FormResponseRedirectToUrlString(url.toString());
    }

    public static FormResponseRedirectToUrlString redirectTo(AssetUrl url) {
        return new FormResponseRedirectToUrlString(url.toString());
    }
}
