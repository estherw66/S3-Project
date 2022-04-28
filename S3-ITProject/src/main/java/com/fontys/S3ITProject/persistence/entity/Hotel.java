package com.fontys.s3itproject.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Hotel {

    private Address address;
    private List<Room> roomList;
    private List<Employee> employeeList;

    public Hotel(Address address){
        this.address = address;

        this.roomList = new ArrayList<>();
        this.employeeList = new ArrayList<>();
    }
}
