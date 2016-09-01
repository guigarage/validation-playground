package com.guigarage.dynamicvalidation.sample;

import com.guigarage.dynamicvalidation.BeanValidator;
import javafx.scene.control.TextField;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ViewControllerVersion2 {

    @Inject
    private TextField nameField;

    @Inject
    private TextField cityField;

    @Inject
    private TextField zipCodeField;

    private final BeanValidator<SampleDataModel> validator;

    public ViewControllerVersion2() {
        BeanValidator<ContactDataModel> contactValidator = BeanValidator.build(ContactDataModel.class);
        contactValidator.withProperty("city", provideText(cityField), markTextField(cityField)).
                withProperty("zipCode", provideText(zipCodeField), markTextField(zipCodeField));

        validator = BeanValidator.build(SampleDataModel.class);
        validator.withProperty("name", provideText(nameField), markTextField(nameField));
        validator.withBeanValidator("contact", contactValidator);
    }

    private Supplier<String> provideText(final TextField textField) {
        return () -> textField.getText();
    }

    private Consumer<Set<ConstraintViolation<String>>> markTextField(final TextField field) {
        return v -> {
            if (v.isEmpty()) {
                field.getStyleClass().remove("error-class");
            } else {
                //TODO: show error at textfield based on violations
                field.getStyleClass().add("error-class");
            }
        };
    }

    public void validate() {
        Set<ConstraintViolation<SampleDataModel>> violations = validator.validate();
        if (violations.isEmpty()) {
            storeInDB();
        }
    }

    private void storeInDB() {
        ContactDataModel contact = new ContactDataModel(cityField.getText(), zipCodeField.getText());
        SampleDataModel model = new SampleDataModel(nameField.getText(), contact);
        //STORE IN DB....
    }
}
