package org.mb4j.controller.form1;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.ControllerResponse;

public class FormActionTest {
  static class SomeForm extends Form1 {
  }

  static class SomeAction extends FormAction1<SomeForm> {
    @Override
    protected ControllerResponse doHandle(ControllerRequest request, SomeForm form) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }

  @Test
  public void internally_creates_instances_of_generic_form_parameter_type() {
    SomeAction action = new SomeAction();
    assertThat(action.createEmptyForm(), instanceOf(SomeForm.class));
  }
}
