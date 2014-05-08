package org.mb4j.servlet;

import org.mb4j.controller.Controller;
import org.mb4j.controller.Request;
import org.mb4j.controller.page.Page;
import org.mb4j.controller.page.PageResponse;

public class TypicalControllers {
  public static class Home extends Page {
    @Override
    public PageResponse handle(Request request) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }

  public static class Document extends Page {
    @Override
    public PageResponse handle(Request request) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }

  public static class DocumentNew extends Page {
    @Override
    public PageResponse handle(Request request) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }

  public static class DocumentEdit extends Page {
    @Override
    public PageResponse handle(Request request) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }
  public static final Controller HOME = new Home();
  public static final Controller DOCUMENT = new Document();
  public static final Controller DOCUMENT_NEW = new DocumentNew();
  public static final Controller DOCUMENT_EDIT = new DocumentEdit();
}
