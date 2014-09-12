package org.mb4j.example.servlet;

import java.io.Writer;
import static org.mb4j.brick.renderer.RendererUtils.renderer4Development;
import org.mb4j.component.ControllerUrl;
import org.mb4j.component.Request;
import org.mb4j.component.utils.AttributesMap;
import org.mb4j.component.utils.ResponseOnWriter;
import org.mb4j.component.viewmap.ViewMap;
import org.mb4j.servlet.ControllerRequest;

public class ServletSampleTestApplication {

    public static <T> T inject(Class<T> klass) {
        return ServletSampleModule.injector.getInstance(klass);
    }

    public static Request requestFor(ControllerUrl url) {
        String path2home = "../../../";
        return ControllerRequest.of(
                url,
                path2home,
                new AttributesMap(),
                inject(ViewMap.class));
    }

    public static ResponseOnWriter responseOn(Writer writer) {
        return new ResponseOnWriter(renderer4Development(), writer);
    }
}
