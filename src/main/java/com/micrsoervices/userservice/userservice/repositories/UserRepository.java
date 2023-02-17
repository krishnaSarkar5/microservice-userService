package com.micrsoervices.userservice.userservice.repositories;

import com.micrsoervices.userservice.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
