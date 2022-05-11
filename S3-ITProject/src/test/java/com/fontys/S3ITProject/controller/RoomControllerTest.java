package com.fontys.s3itproject.controller;

import com.fontys.s3itproject.business.RoomService;
import com.fontys.s3itproject.dto.CreateRoomRequestDTO;
import com.fontys.s3itproject.dto.CreateRoomResponseDTO;
import com.fontys.s3itproject.dto.GetRoomsResponseDTO;
import com.fontys.s3itproject.dto.RoomDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RoomController.class)
class RoomControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RoomService roomServiceMock;

    @Test
    void getAllRooms_shouldReturn200ResponseWithRoomsArray() throws Exception {
        GetRoomsResponseDTO responseDTO = GetRoomsResponseDTO.builder()
                .rooms(List.of(
                        RoomDTO.builder().id(1L).capacity(1).pricePerNight(50)
                                .imageUrl("").roomType("Single").isFeatured(true)
                                .build(),
                        RoomDTO.builder().id(2L).capacity(2).pricePerNight(75)
                                .imageUrl("").roomType("Double").isFeatured(false)
                                .build(),
                        RoomDTO.builder().id(3L).capacity(4).pricePerNight(100)
                                .imageUrl("").roomType("Family").isFeatured(true)
                                .build()
                ))
                .build();
        when(roomServiceMock.getRooms())
                .thenReturn(responseDTO);

        mockMvc.perform(get("/api/rooms"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                    {"rooms":  [{"id": 1, "capacity": 1, "pricePerNight": 50, "imageUrl": "", "roomType": "Single", "featured": true },
                    {"id": 2, "capacity": 2, "pricePerNight": 75, "imageUrl": "", "roomType": "Double", "featured": false },
                    {"id": 3, "capacity": 4, "pricePerNight": 100, "imageUrl": "", "roomType": "Family", "featured": true }]}
                """));
    }

    @Test
    void getFeaturedRooms_shouldReturn200ResponseWithFeaturedRoomsArray() throws Exception {
        GetRoomsResponseDTO responseDTO = GetRoomsResponseDTO.builder()
                .rooms(List.of(
                        RoomDTO.builder().id(1L).capacity(1).pricePerNight(50)
                                .imageUrl("").roomType("Single").isFeatured(true)
                                .build(),
                        RoomDTO.builder().id(3L).capacity(4).pricePerNight(100)
                                .imageUrl("").roomType("Family").isFeatured(true)
                                .build()
                ))
                .build();
        when(roomServiceMock.getFeaturedRooms())
                .thenReturn(responseDTO);

        mockMvc.perform(get("/api/rooms/featured"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                    {"rooms":  [{"id": 1, "capacity": 1, "pricePerNight": 50, "imageUrl": "", "roomType": "Single", "featured": true },
                    {"id": 3, "capacity": 4, "pricePerNight": 100, "imageUrl": "", "roomType": "Family", "featured": true }]}
                """));
    }

    @Test
    void createRoom_shouldCreateRoomAndReturn201_whenRequestValid() throws Exception{
        CreateRoomRequestDTO requestDTO = CreateRoomRequestDTO.builder()
                .capacity(1).pricePerNight(50).imageUrl("").roomType("Single").isFeatured(false)
                .build();
        when(roomServiceMock.createRoom(requestDTO))
                .thenReturn(CreateRoomResponseDTO.builder().roomID(1L).build());

        mockMvc.perform(post("/api/rooms")
                .contentType(APPLICATION_JSON_VALUE)
                .content("""
                            {
                                "capacity": 1,
                                "pricePerNight": 50,
                                "imageUrl": "",
                                "roomType": "Single",
                                "featured": 0
                            }
                        """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {"roomID":  1}
                        """));

        verify(roomServiceMock).createRoom(requestDTO);
    }
}