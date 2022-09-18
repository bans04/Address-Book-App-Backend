package com.bridgelabz.addressbook.repository;
import com.bridgelabz.addressbook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Create UserRepository to interface to connect with database extends it with JpaRepository
 * which provide inbuilt methode
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
