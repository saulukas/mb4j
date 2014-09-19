package org.mb4j.servlet;

import org.mb4j.component.View;
import org.mb4j.component.Request;
import org.mb4j.component.Response;

public abstract class Service implements View {

    @Override
    public void render(Request request, Response response) {
        try {
            serve(request, response);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to serve resource: " + ex, ex);
        }
    }

    protected abstract void serve(Request request, Response response) throws Exception;
}
