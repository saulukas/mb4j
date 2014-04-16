package org.mb4j.servlet;

import org.mb4j.controller.View;
import org.mb4j.controller.ViewRequest;
import org.mb4j.controller.ViewResponse;

public class TypicalViews {
  public static class Home implements View {
    public ViewResponse handle(ViewRequest request) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }

  public static class Document implements View {
    public ViewResponse handle(ViewRequest request) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }

  public static class DocumentNew implements View {
    public ViewResponse handle(ViewRequest request) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }

  public static class DocumentEdit implements View {
    public ViewResponse handle(ViewRequest request) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }
  public static final View HOME = new Home();
  public static final View DOCUMENT = new Document();
  public static final View DOCUMENT_NEW = new DocumentNew();
  public static final View DOCUMENT_EDIT = new DocumentEdit();
}
