package com.bridgelabz.addressbook.exception;

public class UserAllreadyExist extends RuntimeException{
    public UserAllreadyExist(String message) {
        super(message);
    }
}
