package org.mb4j.example.servlet.event.edit;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.ComponentUsingReflection;
import org.mb4j.component.form.Form4Response;
import org.mb4j.component.Request;

@Singleton
public class EventEditPanel extends ComponentUsingReflection {
  @Inject
  EventEditFormHandler formHandler;

  public static class Brick extends MustacheBrick {
    Form4Response form;
  }

  public Brick bakeBrick(Request request, int eventId) {
    Brick brick = new Brick();
    brick.form = request.resolve(formHandler.fillForm(request, eventId));
    return brick;
  }
}
