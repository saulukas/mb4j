package org.mb4j.component.form.data;

import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import static org.mb4j.component.form.data.FormField.optionalField;
import static org.mb4j.component.form.data.FormField.requiredField;

public class FormDataTest {

    public static class EmbeddedData extends FormData {

        public FormField detailA = optionalField().withValue("Some details about something.");
        public FormField detailB = optionalField();
    }

    public static class PersonData extends FormData {

        public FormField name = requiredField().withMaxSize(75);
        public FormField hobby = optionalField();

        public PersonData() {
        }

        public PersonData(String name, String hobby) {
            this.name.value = name;
            this.hobby.value = hobby;
        }
    }

    public static class BaseData extends FormData {

        public FormField companyName = requiredField().withValue("Bricks Ltd.");
        public FormField address = optionalField().withValue("River Street 25");
        public FormField rating = optionalField().withValue("99");
        public FormDataList<PersonData> employees = FormDataList.of(PersonData.class,
                new PersonData("Tom", "music"),
                new PersonData("Ana", "dancing"),
                new PersonData("Jonas", "walking")
        );
    }

    public static class ExtendedData extends BaseData {

        public FormField founder = optionalField().withValue("Jon Jonson");
        public FormField country = optionalField().withValue("Rainland");
        public EmbeddedData embeddedInfo = new EmbeddedData();
    }

    @Test
    public void asFieldMap_maps_field_names_to_FormField_including_inherited_and_nested_ones() {
        FormData fields = new ExtendedData();
        Map<String, FormField> fieldMap = fields.asFieldMap();
        assertEquals(fieldMap.size(), 13);
        assertEquals(fieldMap.get("address").value, "River Street 25");
        assertEquals(fieldMap.get("companyName").value, "Bricks Ltd.");
        assertEquals(fieldMap.get("country").value, "Rainland");
        assertEquals(fieldMap.get("embeddedInfo.detailA").value, "Some details about something.");
        assertEquals(fieldMap.get("embeddedInfo.detailB").value, "");
        assertEquals(fieldMap.get("employees.0.hobby").value, "music");
        assertEquals(fieldMap.get("employees.0.name").value, "Tom");
        assertEquals(fieldMap.get("employees.1.hobby").value, "dancing");
        assertEquals(fieldMap.get("employees.1.name").value, "Ana");
        assertEquals(fieldMap.get("employees.2.hobby").value, "walking");
        assertEquals(fieldMap.get("employees.2.name").value, "Jonas");
        assertEquals(fieldMap.get("founder").value, "Jon Jonson");
        assertEquals(fieldMap.get("rating").value, "99");
    }

    @Test
    public void has_nice_toString() {
        System.out.println("nice toString: " + new ExtendedData());
    }

    private static class NonFieldAttributes extends FormData {

        FormField validField = optionalField();
        Number invalidField;
    }

    @Test
    public void asFieldMap_fails_if_non_FormFieldBase_attributes_are_found() {
        try {
            new NonFieldAttributes().asFieldMap();
            fail();
        } catch (RuntimeException ex) {
            System.out.println("Expected exception: " + ex);
        }
    }
}
