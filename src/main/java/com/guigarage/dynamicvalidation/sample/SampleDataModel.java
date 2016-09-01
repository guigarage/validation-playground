package com.guigarage.dynamicvalidation.sample;

import javax.validation.constraints.NotNull;

/**
 * Created by hendrikebbers on 01.09.16.
 */
public class SampleDataModel {

    @NotNull
    private String name;

    @NotNull
    private ContactDataModel contact;

    public SampleDataModel() {
    }

    public SampleDataModel(String name, ContactDataModel contact) {
        this.name = name;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContactDataModel getContact() {
        return contact;
    }

    public void setContact(ContactDataModel contact) {
        this.contact = contact;
    }
}
