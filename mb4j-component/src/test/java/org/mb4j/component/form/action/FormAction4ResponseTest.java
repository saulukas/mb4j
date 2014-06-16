package org.mb4j.component.form.action;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FormAction4ResponseTest {
  @Test
  public void creates_map_of_attributes_of_FormAction() {
    FormAction action = new FormAction("save");
    action.enabled = false;
    action.visible = true;
    FormAction4Response action4Response = new FormAction4Response("name7", action);
    assertEquals(action4Response.size(), 3);
    assertEquals(action4Response.get("name"), "name7");
    assertEquals(action4Response.get("enabled"), false);
    assertEquals(action4Response.get("visible"), true);
  }

  private static class ActionSubclass extends FormAction {
    String tooltip = "Save currently selected file";

    public ActionSubclass(String name) {
      super(name);
    }
  }

  @Test
  public void includes_attributes_from_subclass() {
    ActionSubclass action = new ActionSubclass("saveFile");
    FormAction4Response action4Response = new FormAction4Response("name7", action);
    assertEquals(action4Response.get("tooltip"), "Save currently selected file");
  }
}
