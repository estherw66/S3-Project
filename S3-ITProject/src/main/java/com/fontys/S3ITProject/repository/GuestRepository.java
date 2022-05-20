package com.fontys.s3itproject.repository;

import com.fontys.s3itproject.repository.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
    boolean existsByEmail(String email);

}
