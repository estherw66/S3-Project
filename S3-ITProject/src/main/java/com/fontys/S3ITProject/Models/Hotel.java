package com.fontys.S3ITProject.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Hotel {

    private String location;
    private List<Room> roomList;
    private List<Employee> employeeList;

    public Hotel(String location){
        this.location = location;

        this.roomList = new ArrayList<>();
        this.employeeList = new ArrayList<>();
    }
}
