package org.mb4j.component.form.response;

import org.mb4j.component.ViewUrl;

public class FormResponseRedirectToView implements FormResponse {

    public final ViewUrl viewUrl;

    private FormResponseRedirectToView(ViewUrl viewUrl) {
        this.viewUrl = viewUrl;
    }

    public static FormResponseRedirectToView redirectTo(ViewUrl viewUrl) {
        return new FormResponseRedirectToView(viewUrl);
    }
}
