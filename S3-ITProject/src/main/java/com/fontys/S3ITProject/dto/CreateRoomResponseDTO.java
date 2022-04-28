package com.fontys.s3itproject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateRoomResponseDTO {
    private Long roomID;
}
