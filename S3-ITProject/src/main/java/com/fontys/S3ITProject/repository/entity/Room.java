package com.fontys.s3itproject.repository.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "room")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Min(1)
    @Max(10)
    @Column(name = "capacity")
    private int capacity;

    @NotNull
    @Column(name = "price_per_night")
    private double pricePerNight;

    @Column(name = "image_url")
    private String imageUrl;

    @NotNull
    @Length(min = 1, max = 25)
    @Column(name = "room_type")
    private String roomType;

    @NotNull
    @Column(name = "is_featured")
    private boolean isFeatured;
}
