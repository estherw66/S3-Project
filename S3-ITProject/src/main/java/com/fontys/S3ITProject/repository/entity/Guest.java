package com.fontys.s3itproject.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "guest")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Guest {
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
}
