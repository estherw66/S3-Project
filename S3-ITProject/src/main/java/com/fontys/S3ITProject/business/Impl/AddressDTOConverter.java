package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.dto.AddressDTO;
import com.fontys.s3itproject.dto.EmployeeDTO;
import com.fontys.s3itproject.repository.entity.Address;
import com.fontys.s3itproject.repository.entity.Employee;

final class AddressDTOConverter {
    private AddressDTOConverter() {

    }

    public static AddressDTO convertToDTO(Address address){
        return AddressDTO.builder()
                .id(address.getId())
                .streetName(address.getStreetName())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .build();
    }
}
