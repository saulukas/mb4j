package org.mb4j.controller.path;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.mb4j.controller.path.UrlPathString.pathStringOf;
import static org.mb4j.controller.path.UrlPathString.urlPath;

public class UrlPathStringTest {
  @Test
  public void viewPath_splits_into_segments_at_slashes() {
    assertThat(urlPath("a").segments(), contains("a"));
    assertThat(urlPath("a/b").segments(), contains("a", "b"));
    assertThat(urlPath("a/b/c").segments(), contains("a", "b", "c"));
  }

  @Test
  public void viewPath_ignores_leading_and_trailing_slashes() {
    assertThat(urlPath("///a///").segments(), contains("a"));
    assertThat(urlPath("///a/b//").segments(), contains("a", "b"));
    assertThat(urlPath("////a/b/c/").segments(), contains("a", "b", "c"));
  }

  @Test
  public void viewPath_omits_empty_segments() {
    assertThat(urlPath("a//b").segments(), contains("a", "b"));
    assertThat(urlPath("a///b").segments(), contains("a", "b"));
    assertThat(urlPath("a//b//c").segments(), contains("a", "b", "c"));
  }

  @Test
  public void viewPath_null_string_or_empty_string_becomes_empty_path() {
    assertThat(urlPath(null).segments(), emptyIterable());
    assertThat(urlPath("").segments(), emptyIterable());
    assertThat(urlPath("/").segments(), emptyIterable());
    assertThat(urlPath("//").segments(), emptyIterable());
    assertThat(urlPath("///").segments(), emptyIterable());
  }

  @Test
  public void pathStringOf_concatenates_segments_using_slash() {
    assertThat(pathStringOf(urlPath("/")), is(""));
    assertThat(pathStringOf(urlPath("/a")), is("a"));
    assertThat(pathStringOf(urlPath("/a/b")), is("a/b"));
    assertThat(pathStringOf(urlPath("/a/b/c")), is("a/b/c"));
  }
}
