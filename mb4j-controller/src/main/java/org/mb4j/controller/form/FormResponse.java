package org.mb4j.controller.form;

import java.util.HashSet;
import java.util.Set;
import org.mb4j.controller.ControllerRequestAttribute;
import org.mb4j.controller.url.ControllerUrl4Request;
import org.mb4j.controller.url.Url4Request;

public abstract class FormResponse {
  public static class Redirect extends FormResponse {
    public final String urlString;

    public Redirect(String urlString) {
      this.urlString = urlString;
    }

    public static Redirect redirectTo(String urlString) {
      return new Redirect(urlString);
    }

    public static Redirect redirectTo(ControllerUrl4Request url) {
      return new Redirect(url.toString());
    }

    public static Redirect redirectTo(Url4Request url) {
      return new Redirect(url.toString());
    }
  }

  public static class RepeatRerendering extends FormResponse {
    public final Set<ControllerRequestAttribute> attributes = new HashSet<>();

    private RepeatRerendering() {
    }

    public static RepeatRerendering repeatRendering() {
      return new RepeatRerendering();
    }

    public RepeatRerendering with(ControllerRequestAttribute attribute) {
      attributes.add(attribute);
      return this;
    }
  }
}
