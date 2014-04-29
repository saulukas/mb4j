package org.mb4j.controller.form;

import java.util.Map;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.mb4j.controller.form.FormField.createOptionalField;

public class FormFieldsTest {
  private static class EmbeddedFormData extends FormFields {
    final FormField detail1 = createOptionalField();
    final FormField detail2 = createOptionalField();
  }

  private static class BaseFormData extends FormFields {
    final FormField companyName = createOptionalField();
    final FormField address = createOptionalField();
    final FormField rating = createOptionalField();
  }

  private static class ExtendedSomeFormData extends BaseFormData {
    final FormField founder = createOptionalField();
    final FormField country = createOptionalField();
    final EmbeddedFormData embeddedInfo = new EmbeddedFormData();
  }

  @Test
  public void asMap_maps_field_names_to_FormFields_includind_inherited_and_nested_ones() {
    FormFields fields = new ExtendedSomeFormData();
    Map<String, FormField> fieldMap = fields.asMap();
    assertThat(fieldMap.keySet(), containsInAnyOrder(
        "companyName",
        "address",
        "rating",
        "founder",
        "country",
        "embeddedInfo.detail1",
        "embeddedInfo.detail2"));
  }
}
