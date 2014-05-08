package org.mb4j.controller;

import org.mb4j.controller.page.Page;

public class TypicalPages {
  public static class Home extends Page {
    @Override
    public void handle(Request request, Response response) {
    }
  }

  public static class Tutorial extends Page {
    @Override
    public void handle(Request request, Response response) {
    }
  }

  public static class TutorialOnEvents extends Page {
    @Override
    public void handle(Request request, Response response) {
    }
  }

  public static class TutorialOnSockets extends Page {
    @Override
    public void handle(Request request, Response response) {
    }
  }

  public static class TutorialTopic extends Page {
    @Override
    public void handle(Request request, Response response) {
    }
  }

  public static class TutorialOtherStuff extends Page {
    @Override
    public void handle(Request request, Response response) {
    }
  }
  public static final Controller HOME = new Home();
  public static final Controller TUTORIAL = new Tutorial();
  public static final Controller TUTORIAL_ON_EVENTS = new TutorialOnEvents();
  public static final Controller TUTORIAL_ON_SOCKETS = new TutorialOnSockets();
  public static final Controller TUTORIAL_TOPIC = new TutorialTopic();
  public static final Controller TUTORIAL_OTHER_STUFF = new TutorialOtherStuff();
}
