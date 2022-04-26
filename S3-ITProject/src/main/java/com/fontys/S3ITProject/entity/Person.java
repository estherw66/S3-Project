package com.fontys.s3itproject.entity;


import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

//@Builder
//@Data
//@MappedSuperclass
@Data
//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private Long id;

//    @NotBlank
//    @Length(min = 2, max = 30)
//    @Column(name = "first_name")
    private String firstName;

//    @NotBlank
//    @Length(min = 2, max = 40)
//    @Column(name = "last_name")
    private String lastName;

//    @NotBlank
//    @Length(min = 2, max = 50)
//    @Column(name = "email")
    private String email;

//    @NotBlank
//    @Length(min = 2, max = 25)
//    @Column(name = "username")
    private String username;

//    @NotBlank
//    @Length(min = 2, max = 25)
//    @Column(name = "password")
    private String password;
}
