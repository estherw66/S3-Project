package com.fontys.s3itproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoomRequestDTO {
    @NotNull
    @Min(1)
    @Max(10)
    private int capacity;

    @NotNull
    private double pricePerNight;

    private String imageUrl;

    @NotNull
    @Length(min = 1, max = 25)
    private String roomType;

    @NotNull
    private boolean isFeatured;
}
