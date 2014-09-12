package org.mb4j.component.form.response;

import org.mb4j.component.ControllerUrl;

public class FormResponseRedirectToView implements FormResponse {

    public final ControllerUrl viewUrl;

    private FormResponseRedirectToView(ControllerUrl viewUrl) {
        this.viewUrl = viewUrl;
    }

    public static FormResponseRedirectToView redirectTo(ControllerUrl viewUrl) {
        return new FormResponseRedirectToView(viewUrl);
    }
}
