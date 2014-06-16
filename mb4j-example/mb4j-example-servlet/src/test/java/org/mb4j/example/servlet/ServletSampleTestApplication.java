package org.mb4j.example.servlet;

import com.google.inject.Injector;
import java.io.Writer;
import static org.mb4j.brick.renderer.RendererUtils.renderer4Development;
import org.mb4j.component.utils.AttributesMap;
import org.mb4j.component.utils.ResponseOnWriter;
import org.mb4j.component.view.ViewRequest;
import org.mb4j.component.view.ViewUrl;
import org.mb4j.component.viewmap.ViewMap;
import org.mb4j.servlet.ControllerRequest;

public class ServletSampleTestApplication {
  static Injector injector = ServletSampleModule.injector();

  public static <T> T inject(Class<T> klass) {
    return injector.getInstance(klass);
  }

  public static ViewRequest requestFor(ViewUrl url) {
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
