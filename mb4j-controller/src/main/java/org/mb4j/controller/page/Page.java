package org.mb4j.controller.page;

import org.mb4j.controller.Component;
import org.mb4j.controller.Controller;
import org.mb4j.controller.Request;

public abstract class Page extends Component implements Controller {
  @Override
  public abstract PageResponse handle(Request request);
}
