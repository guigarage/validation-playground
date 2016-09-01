package com.guigarage.dynamicvalidation.sample;

import javax.validation.constraints.NotNull;

/**
 * Created by hendrikebbers on 01.09.16.
 */
public class ContactDataModel {

    @NotNull
    private String city;

    @NotNull
    private String zipCode;

    public ContactDataModel() {
    }

    public ContactDataModel(String city, String zipCode) {
        this.city = city;
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
