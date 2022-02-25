package com.fontys.S3ITProject.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditCard implements PaymentInfo {

    private String creditCardNumber;
    private String name;
    private String expirationDate;

    public CreditCard(String creditCardNumber, String name, String expirationDate){
        this.creditCardNumber = creditCardNumber;
        this.name = name;
        this.expirationDate = expirationDate;
    }
}
