package com.fontys.s3itproject.entity;

import com.fontys.s3itproject.entity.enums.RoomType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    private RoomType type;

//    @NotBlank
//    @Column(name = "img_url")
    private String imgUrl;

    private boolean isFeatured;
}
