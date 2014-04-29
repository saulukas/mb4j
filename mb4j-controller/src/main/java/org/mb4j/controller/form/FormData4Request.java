package org.mb4j.controller.form;

import org.mb4j.controller.form.field.FormField4Request;
import java.util.Map;
import org.mb4j.brick.Brick;

public class FormData4Request {
  public final Brick header;
  public final Map<String, FormField4Request> fields;
  public final Map<String, FormAction4Request> actions;

  public FormData4Request(Brick header, Map<String, FormField4Request> fields, Map<String, FormAction4Request> actions) {
    this.header = header;
    this.fields = fields;
    this.actions = actions;
  }
}
