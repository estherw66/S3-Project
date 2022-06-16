package com.fontys.s3itproject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GuestReservationDTO {
    private Long guestID;
    private String firstName;
    private String lastName;
}
