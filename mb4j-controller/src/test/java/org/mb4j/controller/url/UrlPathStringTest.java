package org.mb4j.controller.url;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.mb4j.controller.url.UrlPathString.pathStringOf;
import static org.mb4j.controller.url.UrlPathString.urlPathOf;

public class UrlPathStringTest {
  @Test
  public void viewPath_splits_into_segments_at_slashes() {
    assertThat(urlPathOf("a").segments(), contains("a"));
    assertThat(urlPathOf("a/b").segments(), contains("a", "b"));
    assertThat(urlPathOf("a/b/c").segments(), contains("a", "b", "c"));
  }

  @Test
  public void viewPath_ignores_leading_and_trailing_slashes() {
    assertThat(urlPathOf("///a///").segments(), contains("a"));
    assertThat(urlPathOf("///a/b//").segments(), contains("a", "b"));
    assertThat(urlPathOf("////a/b/c/").segments(), contains("a", "b", "c"));
  }

  @Test
  public void viewPath_omits_empty_segments() {
    assertThat(urlPathOf("a//b").segments(), contains("a", "b"));
    assertThat(urlPathOf("a///b").segments(), contains("a", "b"));
    assertThat(urlPathOf("a//b//c").segments(), contains("a", "b", "c"));
  }

  @Test
  public void viewPath_null_string_or_empty_string_becomes_empty_path() {
    assertThat(urlPathOf(null).segments(), emptyIterable());
    assertThat(urlPathOf("").segments(), emptyIterable());
    assertThat(urlPathOf("/").segments(), emptyIterable());
    assertThat(urlPathOf("//").segments(), emptyIterable());
    assertThat(urlPathOf("///").segments(), emptyIterable());
  }

  @Test
  public void pathStringOf_concatenates_segments_using_slash() {
    assertThat(pathStringOf(urlPathOf("/")), is(""));
    assertThat(pathStringOf(urlPathOf("/a")), is("a"));
    assertThat(pathStringOf(urlPathOf("/a/b")), is("a/b"));
    assertThat(pathStringOf(urlPathOf("/a/b/c")), is("a/b/c"));
  }
}
