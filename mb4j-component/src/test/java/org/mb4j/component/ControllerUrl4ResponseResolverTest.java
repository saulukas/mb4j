package org.mb4j.component;

import org.mb4j.component.ControllerUrl4ResponseResolver;
import org.mb4j.component.ControllerUrl;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.mb4j.component.TypicalControllers;
import org.mb4j.component.TypicalControllers.Home;
import static org.mb4j.component.TypicalControllers.TUTORIAL;
import static org.mb4j.component.TypicalControllers.TUTORIAL_ON_EVENTS;
import static org.mb4j.component.TypicalControllers.TUTORIAL_ON_SOCKETS;
import static org.mb4j.component.TypicalControllers.TUTORIAL_OTHER_STUFF;
import static org.mb4j.component.TypicalControllers.TUTORIAL_TOPIC;
import org.mb4j.component.TypicalControllers.TutorialOnEvents;
import org.mb4j.component.TypicalControllers.TutorialOnSockets;
import org.mb4j.component.TypicalControllers.TutorialOtherStuff;
import org.mb4j.component.TypicalControllers.TutorialTopic;
import org.mb4j.component.viewmap.ViewMap;
import org.mb4j.component.viewmap.ViewMapBuilder;
import org.mb4j.component.url.NamedParams;
import org.mb4j.component.url.UrlParams;
import static org.mb4j.component.url.UrlPathString.urlPathOf;
import org.mb4j.component.url.UrlPathStringToHome;

public class ControllerUrl4ResponseResolverTest {
  @Test
  public void resolves_ViewUtl_into_string_taking_into_account_current_path2home() {
    String path2home = UrlPathStringToHome.from("path/from/home");
    ViewMapBuilder builder = ViewMapBuilder
        .routeHomeTo(TypicalControllers.HOME)
        .route(urlPathOf("tutorial/*"), TUTORIAL)
        .route(urlPathOf("tutorial/events"), TUTORIAL_ON_EVENTS)
        .route(urlPathOf("tutorial/sockets"), TUTORIAL_ON_SOCKETS)
        .route(urlPathOf("tutorial/topic/*"), TUTORIAL_TOPIC)
        .route(urlPathOf("tutorial/other/stuff"), TUTORIAL_OTHER_STUFF);
    ControllerUrl4ResponseResolver resolver = new ControllerUrl4ResponseResolver(
        path2home,
        new ViewMap(builder).viewClass2UrlPath());
    assertThat(resolver.resolve(ControllerUrl.of(Home.class)).toString(),
        is("../../"));
    assertThat(resolver.resolve(ControllerUrl.of(TutorialOnEvents.class)).toString(),
        is("../../tutorial/events"));
    assertThat(resolver.resolve(ControllerUrl.of(TutorialOnSockets.class)).toString(),
        is("../../tutorial/sockets"));
    assertThat(resolver.resolve(ControllerUrl.of(TutorialTopic.class, UrlParams.of(urlPathOf("1/2/3")))).toString(),
        is("../../tutorial/topic/1/2/3"));
    assertThat(resolver.resolve(ControllerUrl.of(TutorialOtherStuff.class)).toString(),
        is("../../tutorial/other/stuff"));
    NamedParams namedParams = NamedParams.empty()
        .withReplaced("order", "ascending")
        .withReplaced("removeDuplicates", "")
        .withReplaced("lt_message", "vidur prūdo bliūdas plūdo")
        .withReplaced("name with spaces", "someValue");
    System.out.println("" + namedParams);
    ControllerUrl url = ControllerUrl.of(TutorialTopic.class, UrlParams.of(urlPathOf("127"), namedParams));
    assertThat(resolver.resolve(url).toString(), is("../../tutorial/topic"
        + "/127"
        + "?lt_message=vidur%20pr%C5%ABdo%20bli%C5%ABdas%20pl%C5%ABdo"
        + "&name%20with%20spaces=someValue"
        + "&order=ascending"
        + "&removeDuplicates="));
  }
}
