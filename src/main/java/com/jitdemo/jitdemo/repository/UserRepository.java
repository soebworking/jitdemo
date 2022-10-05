package com.jitdemo.jitdemo.repository;

import com.jitdemo.jitdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
