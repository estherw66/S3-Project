package com.fontys.s3itproject.persistence.entity;

import lombok.*;

//
//@Entity
//@Table(name = "rooms")
////@Builder
//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {

//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @NotNull
//    @Column(name = "max_capacity")
//    @Min(1)
//    @Max(10)
//    @EqualsAndHashCode.Exclude
    private int maxCapacity;

//    @NotBlank
//    @Column(name = "base_price_per_night")
    private double basePricePerNight;

//    @NotBlank
//    @Column(name = "room_type")

//    @NotBlank
//    @Column(name = "img_url")
    private String imgUrl;

    private boolean isFeatured;
}
