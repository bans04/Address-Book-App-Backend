package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.entity.User;
import com.bridgelabz.addressbook.exception.UserAllreadyExist;
import com.bridgelabz.addressbook.exception.UserNotFoundException;
import com.bridgelabz.addressbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * Purpose:-> to add new user data in database
     *
     * @param user
     * @return
     */
    public User createUser(User user) {
//        userRepository.findAll().stream().map(user1 -> {
//            if (!user.getEmail().equals(user1.getEmail())){
//                userRepository.save(user);
//            }else {
//                new UserAllreadyExist("User allready exist with this user email " + user.getEmail());
//            }
//            return null;
//        });
//        return null;
        return userRepository.save(user);
    }

    /**
     * Purpose:-> find users in database by the userId
     *
     * @param userId
     * @return
     */
    public User getUserById(long userId) {
        Optional<User> user = Optional.ofNullable(userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(" No User found with userId " + userId)));
        return user.get();
    }

    /**
     * Purpose:-> get all users data inside the database
     *
     * @return
     */
    public List<User> getUsers() {
        List<User> user = userRepository.findAll();
        return user;
    }

    /**
     * Purpose:-> delete particular user data inside the database by userId
     *
     * @param userId
     * @return
     */
    public User delete(long userId) {
        Optional<User> user = Optional.ofNullable(userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("No User found with userId " + userId)));
        userRepository.delete(user.get());
        return user.get();
    }

    /**
     * Purpose:-> update particular user data inside the database by userId
     *
     * @param userId
     * @param user
     * @return
     */
    public User update(long userId, User user) {
        User user1 = getUserById(userId);
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setPhoneNo(user.getPhoneNo());
        user1.setEmail(user.getEmail());
        userRepository.save(user1);
        return user1;
    }
}
