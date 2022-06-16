package com.fontys.s3itproject.controller;

import com.fontys.s3itproject.business.GuestService;
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

import java.time.LocalDate;
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
class GuestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GuestService guestServiceMock;

    @Test
    @WithMockUser(username = "Esther", roles = {"GUEST"})
    void getGuest_shouldReturn200ResponseWithGuest_whenGuestFound() throws Exception {
        GuestDTO guestDTO = GuestDTO.builder()
                .id(1L)
                .firstName("Esther")
                .build();

        when(guestServiceMock.getGuest(1L)).thenReturn(Optional.of(guestDTO));

        mockMvc.perform(get("/api/guests/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {
                                "id": 1,
                                "firstName": "Esther"
                            }
                    """));

        verify(guestServiceMock).getGuest(1L);
    }

    @Test
    @WithMockUser(username = "Esther", roles = {"GUEST"})
    void getGuest_shouldReturn404_whenGuestNotFound() throws Exception{
        when(guestServiceMock.getGuest(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/guests/1"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(guestServiceMock).getGuest(1L);
    }

    @Test
    @WithMockUser(username = "EstherWolfs", roles = {"ADMIN"})
    void getAllGuests_shouldReturn200ResponseWithGuestsArray() throws Exception{
        GetGuestsResponseDTO responseDTO = GetGuestsResponseDTO.builder()
                .guests(List.of(
                        GuestDTO.builder().id(1L).build(),
                        GuestDTO.builder().id(2L).build()
                )).build();

        when(guestServiceMock.getGuests()).thenReturn(responseDTO);

        mockMvc.perform(get("/api/guests"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {
                                "guests": [{"id": 1}, {"id": 2}]
                            }
                        """));
        verify(guestServiceMock).getGuests();
    }

    @Test
    void createGuest_shouldReturn201ResponseAndCreateGuest_whenRequestValid() throws Exception {
        CreateGuestRequestDTO requestDTO = CreateGuestRequestDTO.builder()
                .firstName("Esther")
                .lastName("Wolfs")
                .email("estherwolfs@hotmail.com")
                .username("esther403")
                .password("password123")
                .build();

        when(guestServiceMock.createGuest(requestDTO))
                .thenReturn(CreateGuestResponseDTO.builder()
                        .guestID(1L).build());

        mockMvc.perform(post("/api/guests")
                .contentType(APPLICATION_JSON_VALUE)
                .content("""
                            {
                                "firstName": "Esther",
                                "lastName": "Wolfs",
                                "email": "estherwolfs@hotmail.com",
                                "username": "esther403",
                                "password": "password123"
                            }
                        """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                            {"guestID":  1}
                        """));

        verify(guestServiceMock).createGuest(requestDTO);
    }

    @Test
    void  createGuest_shouldReturn400ResponseAndNotCreateGuest_whenMissingFields() throws Exception{
        mockMvc.perform(post("/api/guests")
                .contentType(APPLICATION_JSON_VALUE)
                .content("""
                            {
                                "firstName": "",
                                "lastName": "",
                                "email": "",
                                "username": "",
                                "password": ""
                            }
                        """))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            [
                                {"field":  "firstName", "error":  "must not be blank"},
                                {"field":  "lastName", "error":  "must not be blank"},
                                {"field":  "email", "error":  "must not be blank"},
                                {"field":  "username", "error":  "must not be blank"},
                                {"field":  "password", "error":  "must not be blank"},
                                {"field": "firstName", "error":  "length must be between 2 and 25"},
                                {"field": "lastName", "error":  "length must be between 2 and 50"},
                                {"field": "email", "error":  "length must be between 2 and 50"},
                                {"field": "username", "error":  "length must be between 2 and 20"}
                            ]
                        """));

        verifyNoInteractions(guestServiceMock);
    }

    @Test
    @WithMockUser(username = "Esther", roles = {"GUEST"})
    void updateGuest_shouldReturn204ResponseAndUpdateGuest()throws Exception {
        mockMvc.perform(put("/api/guests/1")
                .contentType(APPLICATION_JSON_VALUE)
                .content("""
                            {
                                "firstName": "Esther",
                                "lastName": "Wolfs",
                                "email": "estherwolfs@hotmail.com"
                            }
                        """))
                .andDo(print())
                .andExpect(status().isNoContent());

        UpdateGuestRequestDTO expectedRequest = UpdateGuestRequestDTO.builder()
                .id(1L)
                .firstName("Esther")
                .lastName("Wolfs")
                .email("estherwolfs@hotmail.com")
                .build();
        verify(guestServiceMock).updateGuest(expectedRequest);
    }
}