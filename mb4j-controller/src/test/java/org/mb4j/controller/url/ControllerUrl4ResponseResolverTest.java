package org.mb4j.controller.url;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.mb4j.controller.TypicalControllers;
import org.mb4j.controller.TypicalControllers.Home;
import static org.mb4j.controller.TypicalControllers.TUTORIAL;
import static org.mb4j.controller.TypicalControllers.TUTORIAL_ON_EVENTS;
import static org.mb4j.controller.TypicalControllers.TUTORIAL_ON_SOCKETS;
import static org.mb4j.controller.TypicalControllers.TUTORIAL_OTHER_STUFF;
import static org.mb4j.controller.TypicalControllers.TUTORIAL_TOPIC;
import org.mb4j.controller.TypicalControllers.TutorialOnEvents;
import org.mb4j.controller.TypicalControllers.TutorialOnSockets;
import org.mb4j.controller.TypicalControllers.TutorialOtherStuff;
import org.mb4j.controller.TypicalControllers.TutorialTopic;
import org.mb4j.controller.mapping.ControllerMappings;
import org.mb4j.controller.mapping.ControllerMounter;
import static org.mb4j.controller.url.UrlPathString.urlPathOf;

public class ControllerUrl4ResponseResolverTest {
  @Test
  public void resolves_ControllerUtl_into_string_taking_into_account_current_path2home() {
    String path2home = UrlPathStringToHome.from("path/from/home");
    ControllerMounter mounter = ControllerMounter
        .withHomeController(TypicalControllers.HOME)
        .mount(urlPathOf("tutorial/*"), TUTORIAL)
        .mount(urlPathOf("tutorial/events"), TUTORIAL_ON_EVENTS)
        .mount(urlPathOf("tutorial/sockets"), TUTORIAL_ON_SOCKETS)
        .mount(urlPathOf("tutorial/topic/*"), TUTORIAL_TOPIC)
        .mount(urlPathOf("tutorial/other/stuff"), TUTORIAL_OTHER_STUFF);
    ControllerUrl4ResponseResolver resolver = new ControllerUrl4ResponseResolver(
        path2home,
        new ControllerMappings(mounter).controllerClass2UrlPath());
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
        .withReplacedParam("order", "ascending")
        .withReplacedParam("removeDuplicates", "")
        .withReplacedParam("lt_message", "vidur prūdo bliūdas plūdo")
        .withReplacedParam("name with spaces", "someValue");
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
