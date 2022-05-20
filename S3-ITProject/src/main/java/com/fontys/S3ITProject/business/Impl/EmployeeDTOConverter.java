package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.dto.AddressDTO;
import com.fontys.s3itproject.dto.EmployeeDTO;
import com.fontys.s3itproject.repository.entity.Employee;

final class EmployeeDTOConverter {
    private EmployeeDTOConverter() {

    }

    public static EmployeeDTO convertToDTO(Employee employee){

        AddressDTO dto = AddressDTOConverter.convertToDTO(employee.getAddress());

        return EmployeeDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .dateOfBirth(employee.getDateOfBirth())
                .address(dto)
                .build();
    }
}
