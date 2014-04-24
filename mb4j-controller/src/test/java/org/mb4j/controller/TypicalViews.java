package org.mb4j.controller;

public class TypicalViews {
  public static class Home extends Controller {
    public ControllerResponse handle(ControllerRequest request) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }

  public static class Tutorial extends Controller {
    public ControllerResponse handle(ControllerRequest request) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }

  public static class TutorialOnEvents extends Controller {
    public ControllerResponse handle(ControllerRequest request) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }

  public static class TutorialOnSockets extends Controller {
    public ControllerResponse handle(ControllerRequest request) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }

  public static class TutorialTopic extends Controller {
    public ControllerResponse handle(ControllerRequest request) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }

  public static class TutorialOtherStuff extends Controller {
    public ControllerResponse handle(ControllerRequest request) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }
  public static final Controller HOME = new Home();
  public static final Controller TUTORIAL = new Tutorial();
  public static final Controller TUTORIAL_ON_EVENTS = new TutorialOnEvents();
  public static final Controller TUTORIAL_ON_SOCKETS = new TutorialOnSockets();
  public static final Controller TUTORIAL_TOPIC = new TutorialTopic();
  public static final Controller TUTORIAL_OTHER_STUFF = new TutorialOtherStuff();
}
