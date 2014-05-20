package org.mb4j.component.form;

import java.util.Map;
import org.mb4j.brick.MustacheBrick;

public class FormData4Response {
  public final MustacheBrick header;
  public final Map<String, FormField4Response> fields;
  public final Map<String, FormAction4Response> actions;

  public FormData4Response(MustacheBrick header, Map<String, FormField4Response> fields, Map<String, FormAction4Response> actions) {
    this.header = header;
    this.fields = fields;
    this.actions = actions;
  }
}
