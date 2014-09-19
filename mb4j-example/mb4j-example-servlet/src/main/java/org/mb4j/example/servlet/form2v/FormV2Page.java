package org.mb4j.example.servlet.form2v;

import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.Request;
import org.mb4j.component.Response;
import org.mb4j.component.ViewLocator;
import org.mb4j.component.form.ActionResponse;
import org.mb4j.component.url.NamedParams;
import org.mb4j.example.servlet.master.MasterLayoutPage;

public class FormV2Page extends MasterLayoutPage {

    public static ViewLocator locator() {
        return ViewLocator.of(FormV2Page.class);
    }

    @Override
    protected MustacheBrick bakeContentBrick(Request request) {
        FormV2Brick brick = new FormV2Brick();
        brick.resourceUrl = request.resourceUrl(this, "addTwoThings");
        return brick;
    }

    @ResourceMethod
    void addTwoThings(Request request, Response response) {
        response.write("Two things");
    }

    @ActionMethod
    ActionResponse submit(Request request, NamedParams formInput) {
        return null;
    }
}
