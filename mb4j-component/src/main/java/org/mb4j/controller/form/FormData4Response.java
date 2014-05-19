package org.mb4j.controller.form;

import java.util.Map;
import org.mb4j.brick.Brick;

public class FormData4Response {
  public final Brick header;
  public final Map<String, FormField4Response> fields;
  public final Map<String, FormAction4Response> actions;

  public FormData4Response(Brick header, Map<String, FormField4Response> fields, Map<String, FormAction4Response> actions) {
    this.header = header;
    this.fields = fields;
    this.actions = actions;
  }
}
