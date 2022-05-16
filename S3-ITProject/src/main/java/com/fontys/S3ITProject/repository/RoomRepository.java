package com.fontys.s3itproject.repository;

import com.fontys.s3itproject.repository.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
