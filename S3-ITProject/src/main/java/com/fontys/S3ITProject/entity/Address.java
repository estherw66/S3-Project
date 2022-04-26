package com.fontys.s3itproject.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

//@Entity
//@Table(name = "address")
//@Builder
//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private Long id;

//    @NotBlank
//    @Length(min = 2, max = 50)
//    @Column(name = "street_name")
    private String streetName;

//    @NotBlank
//    @Length(min = 6, max = 6)
//    @Column(name = "zip_code")
    private String zipCode;

//    @NotBlank
//    @Length(min = 2, max = 30)
//    @Column(name = "city")
    private String city;
}
