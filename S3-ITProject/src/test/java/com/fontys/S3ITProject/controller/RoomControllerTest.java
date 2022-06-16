package com.fontys.s3itproject.controller;

import com.fontys.s3itproject.business.RoomService;
import com.fontys.s3itproject.dto.*;
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
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
                                .totalAmountInHotel(10)
                                .build(),
                        RoomDTO.builder().id(2L).capacity(2).pricePerNight(75)
                                .imageUrl("").roomType("Double").isFeatured(false)
                                .totalAmountInHotel(15)
                                .build(),
                        RoomDTO.builder().id(3L).capacity(4).pricePerNight(100)
                                .imageUrl("").roomType("Family").isFeatured(true)
                                .totalAmountInHotel(20)
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
                    {"rooms":  [{"id": 1, "capacity": 1, "pricePerNight": 50, "imageUrl": "", "roomType": "Single", "featured": true, "totalAmountInHotel": 10 },
                    {"id": 2, "capacity": 2, "pricePerNight": 75, "imageUrl": "", "roomType": "Double", "featured": false, "totalAmountInHotel": 15 },
                    {"id": 3, "capacity": 4, "pricePerNight": 100, "imageUrl": "", "roomType": "Family", "featured": true, "totalAmountInHotel": 20 }]}
                """));
    }

    @Test
    void getFeaturedRooms_shouldReturn200ResponseWithFeaturedRoomsArray() throws Exception {
        GetRoomsResponseDTO responseDTO = GetRoomsResponseDTO.builder()
                .rooms(List.of(
                        RoomDTO.builder().id(1L).capacity(1).pricePerNight(50)
                                .imageUrl("").roomType("Single").isFeatured(true)
                                .totalAmountInHotel(10)
                                .build(),
                        RoomDTO.builder().id(3L).capacity(4).pricePerNight(100)
                                .imageUrl("").roomType("Family").isFeatured(true)
                                .totalAmountInHotel(15)
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
                    {"rooms":  [{"id": 1, "capacity": 1, "pricePerNight": 50, "imageUrl": "", "roomType": "Single", "featured": true, "totalAmountInHotel": 10 },
                    {"id": 3, "capacity": 4, "pricePerNight": 100, "imageUrl": "", "roomType": "Family", "featured": true, "totalAmountInHotel": 15 }]}
                """));
    }

    @Test
    @WithMockUser(username = "EstherWolfs", roles = {"EMPLOYEE"})
    void createRoom_shouldCreateRoomAndReturn201_whenRequestValid() throws Exception{
        CreateRoomRequestDTO requestDTO = CreateRoomRequestDTO.builder()
                .capacity(1).pricePerNight(50).imageUrl("").roomType("Single").isFeatured(false)
                .totalAmountInHotel(10)
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
                                "featured": 0,
                                "totalAmountInHotel": 10
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
                                "featured": 0,
                                "totalAmountInHotel": 0
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

    @Test
    @WithMockUser(username = "EstherWolfs", roles = {"EMPLOYEE"})
    void getRoom_shouldReturn200WithRoom() throws Exception {
        RoomDTO roomDTO = RoomDTO.builder()
                .id(1L)
                .roomType("Single")
                .build();

        when(roomServiceMock.getRoom(1L)).thenReturn(Optional.of(roomDTO));

        mockMvc.perform(get("/api/rooms/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {
                                "id": 1,
                                "roomType": "Single"
                            }
                    """));

        verify(roomServiceMock).getRoom(1L);
    }

    @Test
    @WithMockUser(username = "EstherWolfs", roles = {"EMPLOYEE"})
    void getRoom_shouldReturn404NotFoundError_whenEmployeeNotFound() throws Exception{
        when(roomServiceMock.getRoom(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/rooms/1"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(roomServiceMock).getRoom(1L);
    }

    @Test
    @WithMockUser(username = "EstherWolfs", roles = {"GUEST"})
    void getRoom_shouldReturn500InternalServerError_whenLoggedInUserIsNotEmployee() throws Exception{
        mockMvc.perform(get("/api/rooms/1"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }


    @Test
    @WithMockUser(username = "EstherWolfs", roles = {"EMPLOYEE"})
    void updateRoom_shouldUpdatePriceAndReturn204_whenUpdatingRoom() throws Exception{
        mockMvc.perform(put("/api/rooms/1")
                .contentType(APPLICATION_JSON_VALUE)
                .content("""
                            {
                                "pricePerNight": 75,
                                "imageUrl": "not null",
                                "totalAmountInHotel": 10
                            }
                        """))
                .andDo(print())
                .andExpect(status().isNoContent());

                UpdateRoomRequestDTO expectedRequest = UpdateRoomRequestDTO.builder()
                        .id(1L)
                        .pricePerNight(75)
                        .imageUrl("not null")
                        .totalAmountInHotel(10)
                        .build();
                verify(roomServiceMock).updateRoom(expectedRequest);
    }
}