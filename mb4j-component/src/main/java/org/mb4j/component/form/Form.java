package org.mb4j.component.form;

import java.util.Collection;
import org.mb4j.component.form.action.FormAction;
import org.mb4j.component.form.data.FormData;

public class Form<T extends FormData> {

    public final Class<? extends FormHandler> handlerClass;
    public final T data;
    public final Collection<FormAction> actions;

    public Form(Class<? extends FormHandler> handlerClass, T data, Collection<FormAction> actions) {
        this.handlerClass = handlerClass;
        this.data = data;
        this.actions = actions;
    }
}
