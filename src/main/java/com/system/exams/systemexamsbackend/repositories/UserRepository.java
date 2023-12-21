package com.system.exams.systemexamsbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.exams.systemexamsbackend.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
