package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.dto.ResponseDto;
import com.bridgelabz.addressbook.dto.UserDto;
import com.bridgelabz.addressbook.entity.User;
import com.bridgelabz.addressbook.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    /**
     * ModelMapper to convert entity into dto and vice-versa
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Purpose:-> to accept create request and process it
     *
     * @param user
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> addUser( @RequestBody User user) {
        User user1 = userService.createUser(user);
        UserDto userDto = modelMapper.map(user1, UserDto.class);
        ResponseDto responseDto = new ResponseDto("Your data added successfully", userDto);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    /**
     * Purpose:-> to accept get request and process it
     * Url:-> localhost:8080/user/user?userId=?
     *
     * @param userId
     * @return
     */
    @GetMapping("/user")
    public ResponseEntity<ResponseDto> getUserById(@RequestParam int userId) {
        User user = userService.getUserById(userId);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        ResponseDto responseDto = new ResponseDto("Your data retrieve successfully", userDto);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    /**
     * Purpose:-> to accept get request and process it
     * Url:-> localhost:8080/user/users
     *
     * @return
     */
    @GetMapping("/users")
    public ResponseEntity<ResponseDto> getUsers() {
        List<User> user = userService.getUsers();
//        UserDto userDto = modelMapper.map(user, UserDto.class);
        ResponseDto responseDto = new ResponseDto("Your data retrieve successfully", user);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteUser(@RequestParam long userId) {
        User user = userService.delete(userId);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        ResponseDto responseDto = new ResponseDto("Your data deleted successfully", userDto);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateUser(@RequestParam long userId,@Valid @RequestBody User user) {
        User user1 = userService.update(userId, user);
        UserDto userDto = modelMapper.map(user1, UserDto.class);
        ResponseDto responseDto = new ResponseDto("Your data updated successfully", userDto);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }
}
