package com.fontys.s3itproject.repository;

import com.fontys.s3itproject.repository.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
