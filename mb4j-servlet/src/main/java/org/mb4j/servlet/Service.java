package org.mb4j.servlet;

import org.mb4j.component.Controller;
import org.mb4j.component.Request;
import org.mb4j.component.Response;

public abstract class Service implements Controller {

    @Override
    public void handle(Request request, Response response) {
        try {
            serve(request, response);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to serve resource: " + ex, ex);
        }
    }

    protected abstract void serve(Request request, Response response) throws Exception;
}
