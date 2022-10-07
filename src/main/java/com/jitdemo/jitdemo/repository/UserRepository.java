package com.jitdemo.jitdemo.repository;

import com.jitdemo.jitdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    String existsByEmail(String email);

}
