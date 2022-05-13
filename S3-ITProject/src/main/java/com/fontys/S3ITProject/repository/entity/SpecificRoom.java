package com.fontys.s3itproject.repository.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "specific_room")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecificRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
/*
    private int roomNumber;
    private double pricePerNight;
    private boolean isAvailable;
    private Room roomType;
*/
}

