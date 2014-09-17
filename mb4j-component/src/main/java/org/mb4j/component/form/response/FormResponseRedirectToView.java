package org.mb4j.component.form.response;

import org.mb4j.component.ViewLocator;

public class FormResponseRedirectToView implements FormResponse {

    public final ViewLocator viewLocator;

    private FormResponseRedirectToView(ViewLocator viewLocator) {
        this.viewLocator = viewLocator;
    }

    public static FormResponseRedirectToView redirectTo(ViewLocator viewLocator) {
        return new FormResponseRedirectToView(viewLocator);
    }
}
