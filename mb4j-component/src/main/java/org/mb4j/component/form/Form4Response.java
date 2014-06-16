package org.mb4j.component.form;

import java.util.Map;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.form.action.FormAction4Response;
import org.mb4j.component.form.data.FormField4Response;

public class Form4Response {
  public final MustacheBrick headerBrick;
  public final Map<String, FormField4Response> data;
  public final Map<String, FormAction4Response> actions;

  public Form4Response(MustacheBrick headerBrick, Map<String, FormField4Response> data, Map<String, FormAction4Response> actions) {
    this.headerBrick = headerBrick;
    this.data = data;
    this.actions = actions;
  }
}
