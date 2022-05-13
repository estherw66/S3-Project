package com.fontys.s3itproject.controller;

import com.fontys.s3itproject.business.RoomService;
import com.fontys.s3itproject.dto.CreateRoomRequestDTO;
import com.fontys.s3itproject.dto.CreateRoomResponseDTO;
import com.fontys.s3itproject.dto.GetRoomsResponseDTO;
import com.fontys.s3itproject.dto.RoomDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
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
@SpringBootTest
@AutoConfigureMockMvc
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
    @WithMockUser(username = "EstherWolfs", roles = {"EMPLOYEE"})
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

    @Test
    void createRoom_shouldNotCreateRoomAndReturn400_WhenMissingFields() throws Exception {
        mockMvc.perform(post("/api/rooms")
                .contentType(APPLICATION_JSON_VALUE)
                .content("""
                            {
                                "capacity": 0,
                                "pricePerNight": 0,
                                "imageUrl": "",
                                "roomType": "",
                                "featured": 0
                            }
                        """))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                                                        [
                                                            {"field": "pricePerNight", "error":  "must be greater than or equal to 45"},
                                                            {"field": "roomType", "error":  "must not be blank"},
                                                            {"field": "capacity", "error":  "must be greater than or equal to 1"},
                                                            {"field": "roomType", "error":  "length must be between 1 and 25"}
                                                        ]
                                                    """));
        verifyNoInteractions(roomServiceMock);
    }
}