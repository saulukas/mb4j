package org.mb4j.example.servlet.master;

import com.google.inject.Singleton;
import java.io.IOException;
import java.util.Date;
import org.mb4j.component.ComponentUsingReflection;
import org.mb4j.component.Request;
import org.mb4j.component.Response;
import org.mb4j.component.resource.ResourceMethod;
import org.mb4j.example.servlet.services.TimeService;

@Singleton
public class FooterPanel extends ComponentUsingReflection {

    public static final FooterPanel INSTANCE = new FooterPanel();

    private FooterPanel() {
    }

    FooterPanelBrick bakeBrick(Request request) {
        FooterPanelBrick brick = new FooterPanelBrick();
        brick.timeServiceUrl = request.resolve(TimeService.url());
        brick.resources = request.resolveResourcesOf(this);
        return brick;
    }

    @ResourceMethod
    public void time(Request request, Response response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("" + new Date());
    }
}
