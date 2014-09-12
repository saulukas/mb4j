package org.mb4j.component;

public class TypicalControllers {
  public static class Home implements Controller {
    @Override
    public void handle(Request request, Response response) {
    }
  }

  public static class Tutorial implements Controller {
    @Override
    public void handle(Request request, Response response) {
    }
  }

  public static class TutorialOnEvents implements Controller {
    @Override
    public void handle(Request request, Response response) {
    }
  }

  public static class TutorialOnSockets implements Controller {
    @Override
    public void handle(Request request, Response response) {
    }
  }

  public static class TutorialTopic implements Controller {
    @Override
    public void handle(Request request, Response response) {
    }
  }

  public static class TutorialOtherStuff implements Controller {
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
