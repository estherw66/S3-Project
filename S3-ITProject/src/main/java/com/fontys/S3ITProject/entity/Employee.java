package com.fontys.s3itproject.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

//@Entity
//@Table(name = "employee")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Getter
//@Setter
public class Employee extends Person{

//    @OneToOne
    private Address address;

//    @NotBlank
//    @Length(min = 12, max = 12)
//    @Column(name = "phone_number")
    private String phoneNumber;

//    @NotBlank
//    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
//
    public Employee(Long id, String firstName, String lastName, String email, String username, String password, Address address, String phoneNumber, LocalDate dateOfBirth) {
        super(id, firstName, lastName, email, username, password);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }
//
//    public Employee(Address address, String phoneNumber, LocalDate dateOfBirth) {
//        this.address = address;
//        this.phoneNumber = phoneNumber;
//        this.dateOfBirth = dateOfBirth;
//    }
//
//    public Employee() {
//
//    }
}
