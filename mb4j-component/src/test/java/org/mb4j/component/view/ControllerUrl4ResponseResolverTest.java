package org.mb4j.component.view;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.mb4j.component.TypicalViews;
import org.mb4j.component.TypicalViews.Home;
import static org.mb4j.component.TypicalViews.TUTORIAL;
import static org.mb4j.component.TypicalViews.TUTORIAL_ON_EVENTS;
import static org.mb4j.component.TypicalViews.TUTORIAL_ON_SOCKETS;
import static org.mb4j.component.TypicalViews.TUTORIAL_OTHER_STUFF;
import static org.mb4j.component.TypicalViews.TUTORIAL_TOPIC;
import org.mb4j.component.TypicalViews.TutorialOnEvents;
import org.mb4j.component.TypicalViews.TutorialOnSockets;
import org.mb4j.component.TypicalViews.TutorialOtherStuff;
import org.mb4j.component.TypicalViews.TutorialTopic;
import org.mb4j.component.viewmap.SiteMap;
import org.mb4j.component.viewmap.SiteMapBuilder;
import org.mb4j.component.url.NamedParams;
import org.mb4j.component.url.UrlParams;
import static org.mb4j.component.url.UrlPathString.urlPathOf;
import org.mb4j.component.url.UrlPathStringToHome;

public class ControllerUrl4ResponseResolverTest {
  @Test
  public void resolves_ControllerUtl_into_string_taking_into_account_current_path2home() {
    String path2home = UrlPathStringToHome.from("path/from/home");
    SiteMapBuilder builder = SiteMapBuilder
        .withHomeController(TypicalViews.HOME)
        .mount(urlPathOf("tutorial/*"), TUTORIAL)
        .mount(urlPathOf("tutorial/events"), TUTORIAL_ON_EVENTS)
        .mount(urlPathOf("tutorial/sockets"), TUTORIAL_ON_SOCKETS)
        .mount(urlPathOf("tutorial/topic/*"), TUTORIAL_TOPIC)
        .mount(urlPathOf("tutorial/other/stuff"), TUTORIAL_OTHER_STUFF);
    ViewUrl4ResponseResolver resolver = new ViewUrl4ResponseResolver(
        path2home,
        new SiteMap(builder).controllerClass2UrlPath());
    assertThat(resolver.resolve(ViewUrl.of(Home.class)).toString(),
        is("../../"));
    assertThat(resolver.resolve(ViewUrl.of(TutorialOnEvents.class)).toString(),
        is("../../tutorial/events"));
    assertThat(resolver.resolve(ViewUrl.of(TutorialOnSockets.class)).toString(),
        is("../../tutorial/sockets"));
    assertThat(resolver.resolve(ViewUrl.of(TutorialTopic.class, UrlParams.of(urlPathOf("1/2/3")))).toString(),
        is("../../tutorial/topic/1/2/3"));
    assertThat(resolver.resolve(ViewUrl.of(TutorialOtherStuff.class)).toString(),
        is("../../tutorial/other/stuff"));
    NamedParams namedParams = NamedParams.empty()
        .withReplaced("order", "ascending")
        .withReplaced("removeDuplicates", "")
        .withReplaced("lt_message", "vidur prūdo bliūdas plūdo")
        .withReplaced("name with spaces", "someValue");
    System.out.println("" + namedParams);
    ViewUrl url = ViewUrl.of(TutorialTopic.class, UrlParams.of(urlPathOf("127"), namedParams));
    assertThat(resolver.resolve(url).toString(), is("../../tutorial/topic"
        + "/127"
        + "?lt_message=vidur%20pr%C5%ABdo%20bli%C5%ABdas%20pl%C5%ABdo"
        + "&name%20with%20spaces=someValue"
        + "&order=ascending"
        + "&removeDuplicates="));
  }
}
