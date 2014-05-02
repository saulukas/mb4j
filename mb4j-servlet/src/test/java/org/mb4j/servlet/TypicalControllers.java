package org.mb4j.servlet;

import org.mb4j.controller.Controller;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.ControllerResponse;

public class TypicalControllers {
  public static class Home extends Controller {
    @Override
    public ControllerResponse handle(ControllerRequest request) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }

  public static class Document extends Controller {
    @Override
    public ControllerResponse handle(ControllerRequest request) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }

  public static class DocumentNew extends Controller {
    @Override
    public ControllerResponse handle(ControllerRequest request) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }

  public static class DocumentEdit extends Controller {
    @Override
    public ControllerResponse handle(ControllerRequest request) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }
  public static final Controller HOME = new Home();
  public static final Controller DOCUMENT = new Document();
  public static final Controller DOCUMENT_NEW = new DocumentNew();
  public static final Controller DOCUMENT_EDIT = new DocumentEdit();
}
