package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.business.AccessTokenEncoder;
import com.fontys.s3itproject.business.LoginService;
import com.fontys.s3itproject.business.exception.InvalidCredentialsException;
import com.fontys.s3itproject.dto.AccessTokenDTO;
import com.fontys.s3itproject.dto.LoginRequestDTO;
import com.fontys.s3itproject.dto.LoginResponseDTO;
import com.fontys.s3itproject.repository.UserRepository;
import com.fontys.s3itproject.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if (user == null){
            throw new InvalidCredentialsException("USERNAME_DOESNT_EXIST");
        }

        if (!matchesPassword(loginRequest.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException();
        }

        String accessToken = generateAccessToken(user);
        return LoginResponseDTO.builder()
                .accessToken(accessToken)

                .build();
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword){
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public String generateAccessToken(User user){
        Long employeeID = user.getEmployee() != null ? user.getEmployee().getId() : user.getGuest() != null ? user.getGuest().getId() : null;

        List<String> roles = user.getUserRoles().stream()
                .map(userRole -> userRole.getRole().toString())
                .toList();

        return accessTokenEncoder.encode(
                AccessTokenDTO.builder()
                        .subject(user.getUsername())
                        .roles(roles)
                        .employeeId(employeeID)
                        .build());
    }
}
