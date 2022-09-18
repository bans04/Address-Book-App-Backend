package com.bridgelabz.addressbook.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * This is our user entity class and its fields
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    @NotEmpty(message = "First name should not be empty.")
    @Pattern(regexp = "^[A-Z][A-Za-z]{3,}$", message = "Invalid user first name.")
    private String firstName;

    @NotEmpty(message = "Last name should not be empty.")
    @Pattern(regexp = "^[A-Z][A-Za-z]{3,}$", message = "Invalid user last name.")
    private String lastName;

//    @Pattern(regexp = "^([0]|\\+91)?[789][0-9]{9}$", message = "Invalid phone no.")
    private long phoneNo;

//    @Pattern(regexp = "^[a-z.+_-]$", message = "Invalid E-Mail.")
    private String email;

    @NotEmpty
    @ElementCollection
    @CollectionTable(name = "address_table", joinColumns = @JoinColumn(name = "userId"))
    private List<String> address;
}
