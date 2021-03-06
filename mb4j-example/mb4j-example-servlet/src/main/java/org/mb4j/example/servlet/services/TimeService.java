package org.mb4j.example.servlet.services;

import com.google.inject.Singleton;
import java.util.Date;
import org.mb4j.component.ViewLocator;
import org.mb4j.component.Request;
import org.mb4j.component.Response;
import org.mb4j.servlet.Service;

@Singleton
public class TimeService extends Service {

    public static ViewLocator url() {
        return ViewLocator.of(TimeService.class);
    }

    @Override
    protected void serve(Request request, Response response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("" + new Date());
    }
}
