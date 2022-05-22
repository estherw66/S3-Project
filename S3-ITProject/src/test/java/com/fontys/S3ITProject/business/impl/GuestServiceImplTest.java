package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.business.GuestService;
import com.fontys.s3itproject.dto.AccessTokenDTO;
import com.fontys.s3itproject.repository.GuestRepository;
import com.fontys.s3itproject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GuestServiceImplTest {

    @Mock
    GuestRepository guestRepositoryMock;

    @Mock
    UserRepository userRepositoryMock;

    @Mock
    AccessTokenDTO accessTockenDTO;

    @InjectMocks
    GuestService guestServiceMock;

    @Test
    void createGuest_shouldSaveNewGuest() {
        when(guestRepositoryMock.existsByEmail("estherwolfs@hotmail.com")).thenReturn(false);
    }

    @Test
    void getGuests() {
    }

    @Test
    void getGuest() {
    }

    @Test
    void updateGuest() {
    }
}