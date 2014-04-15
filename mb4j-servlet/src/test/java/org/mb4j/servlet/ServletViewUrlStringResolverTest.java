package org.mb4j.servlet;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.mb4j.http.HttpPathToHome.pathStringToHomeFrom;
import org.mb4j.servlet.TypicalViews.DocumentEdit;
import org.mb4j.servlet.TypicalViews.DocumentNew;
import org.mb4j.servlet.TypicalViews.Home;
import org.mb4j.view.NamedParams;
import org.mb4j.view.ViewParams;
import org.mb4j.view.mount.ViewMounter;
import static org.mb4j.view.path.ViewPathString.viewPath;
import org.mb4j.view.url.ViewUrl;

public class ServletViewUrlStringResolverTest {
  @Test
  public void resolves_url_string_for_a_ViewUrl_taking_into_account_current_path2home() {
    String path2home = pathStringToHomeFrom("path/from/home");
    ViewMounter mounter = ViewMounter
        .withHomeView(TypicalViews.HOME)
        .mount(viewPath("document/*"), TypicalViews.DOCUMENT)
        .mount(viewPath("document/new"), TypicalViews.DOCUMENT_NEW)
        .mount(viewPath("document/edit/*"), TypicalViews.DOCUMENT_EDIT);
    ServletViewUrlStringResolver resolver = new ServletViewUrlStringResolver(
        path2home,
        mounter.pathFromViewClassResolver());
    assertThat(resolver.urlStringOf(ViewUrl.of(Home.class)), is("../../"));
    assertThat(resolver.urlStringOf(ViewUrl.of(DocumentNew.class)), is("../../document/new"));
    assertThat(resolver.urlStringOf(ViewUrl.of(DocumentEdit.class)), is("../../document/edit"));
    assertThat(resolver.urlStringOf(ViewUrl.of(DocumentNew.class, ViewParams.of(viewPath("1/2/3")))),
        is("../../document/new/1/2/3"));
    assertThat(resolver.urlStringOf(ViewUrl.of(DocumentEdit.class, ViewParams.of(viewPath("a/b/c")))),
        is("../../document/edit/a/b/c"));
    NamedParams namedParams = NamedParams.empty()
        .withReplacedParam("order", "ascending")
        .withReplacedParam("removeDuplicates", "")
        .withReplacedParam("lt_message", "vidur prūdo bliūdas plūdo")
        .withReplacedParam("name with spaces", "someValue");
    System.out.println("" + namedParams);
    ViewUrl url = ViewUrl.of(DocumentEdit.class, ViewParams.of(viewPath("127"), namedParams));
    assertThat(resolver.urlStringOf(url), is("../../document/edit"
        + "/127"
        + "?lt_message=vidur%20pr%C5%ABdo%20bli%C5%ABdas%20pl%C5%ABdo"
        + "&name%20with%20spaces=someValue"
        + "&order=ascending"
        + "&removeDuplicates="));
  }
}
