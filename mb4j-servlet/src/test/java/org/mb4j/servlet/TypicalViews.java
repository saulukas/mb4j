package org.mb4j.servlet;

import org.mb4j.controller.Controller;
import org.mb4j.controller.ViewRequest;
import org.mb4j.controller.ViewResponse;

public class TypicalViews {
  public static class Home implements Controller {
    public ViewResponse handle(ViewRequest request) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }

  public static class Document implements Controller {
    public ViewResponse handle(ViewRequest request) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }

  public static class DocumentNew implements Controller {
    public ViewResponse handle(ViewRequest request) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }

  public static class DocumentEdit implements Controller {
    public ViewResponse handle(ViewRequest request) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }
  public static final Controller HOME = new Home();
  public static final Controller DOCUMENT = new Document();
  public static final Controller DOCUMENT_NEW = new DocumentNew();
  public static final Controller DOCUMENT_EDIT = new DocumentEdit();
}
