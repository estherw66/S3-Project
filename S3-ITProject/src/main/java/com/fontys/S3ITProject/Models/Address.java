package com.fontys.S3ITProject.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {

    private String streetName;
    private String zipCode;
    private String city;

    public Address(String streetName, String zipCode, String city){
        this.streetName = streetName;
        this.zipCode = zipCode;
        this.city = city;
    }
}
