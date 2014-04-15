package org.mb4j.view.form;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.mb4j.view.ViewRequest;
import org.mb4j.view.ViewResponse;

public class FormActionTest {
  static class SomeForm extends Form {
  }

  static class SomeAction extends FormAction<SomeForm> {
    @Override
    protected ViewResponse doHandle(ViewRequest request, SomeForm form) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }

  @Test
  public void internally_creates_instances_of_generic_form_parameter_type() {
    SomeAction action = new SomeAction();
    assertThat(action.createEmptyForm(), instanceOf(SomeForm.class));
  }
}
