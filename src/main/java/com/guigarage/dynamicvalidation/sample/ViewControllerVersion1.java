package com.guigarage.dynamicvalidation.sample;

import com.guigarage.dynamicvalidation.BeanValidator;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import java.util.Set;

public class ViewControllerVersion1 {

    @Inject
    private TextField nameField;

    @Inject
    private TextField cityField;

    @Inject
    private TextField zipCodeField;

    private final BeanValidator<SampleDataModel> validator;

    public ViewControllerVersion1() {
        BeanValidator<ContactDataModel> contactValidator = BeanValidator.build(ContactDataModel.class);
        contactValidator.withProperty("city", () -> cityField.getText()).
                withProperty("zipCode", () -> zipCodeField.getText());

        validator = BeanValidator.build(SampleDataModel.class);
        validator.withProperty("name", () -> nameField.getText());
        validator.withBeanValidator("contact", contactValidator);
    }

    public void validate() {
        Set<ConstraintViolation<SampleDataModel>> violations = validator.validate();
        if(violations.isEmpty()) {
            storeInDB();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ValidationError");
            alert.show();
        }
    }

    private void storeInDB() {
        ContactDataModel contact = new ContactDataModel(cityField.getText(), zipCodeField.getText());
        SampleDataModel model = new SampleDataModel(nameField.getText(), contact);
        //STORE IN DB....
    }
}
