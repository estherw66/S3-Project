package com.fontys.s3itproject.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "employee")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Length(min = 2, max = 25)
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Length(min = 2, max = 35)
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Length(min = 2, max = 50)
    @Column(name = "email")
    private String email;

    @NotNull
    @Length(min = 12, max = 12)
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
}
