package com.bridgelabz.addressbook.dto;

import lombok.Data;

@Data
public class UserDto {
    private long userId;
    private String firstName;
    private String lastName;
    private long phoneNo;
    private String email;
    private String address;
}
