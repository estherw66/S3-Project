package com.fontys.s3itproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoomRequestDTO {
    private Long id;

    @NotNull
    @Min(45)
    private double pricePerNight;

    private String imageUrl;

    @NotNull
    @Min(0)
    private int totalAmountInHotel;
}
