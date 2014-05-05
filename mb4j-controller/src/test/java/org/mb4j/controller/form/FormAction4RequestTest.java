package org.mb4j.controller.form;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FormAction4RequestTest {
  @Test
  public void creates_map_of_attributes_of_FormAction() {
    FormAction action = new FormAction("save");
    action.enabled = false;
    action.visible = true;
    FormAction4Request action4request = new FormAction4Request("name7", action);
    assertEquals(action4request.size(), 3);
    assertEquals(action4request.get("name"), "name7");
    assertEquals(action4request.get("enabled"), false);
    assertEquals(action4request.get("visible"), true);
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
    FormAction4Request action4request = new FormAction4Request("name7", action);
    assertEquals(action4request.get("tooltip"), "Save currently selected file");
  }
}
