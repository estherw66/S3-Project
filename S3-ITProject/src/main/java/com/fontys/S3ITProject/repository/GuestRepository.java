package com.fontys.s3itproject.repository;

import com.fontys.s3itproject.repository.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuestRepository extends JpaRepository<Guest, Long> {
    boolean existsByEmail(String email);
    Optional<Guest> findById(Long id);
}
