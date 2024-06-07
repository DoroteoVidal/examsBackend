package com.system.exams.systemexamsbackend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.exams.systemexamsbackend.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    Optional<User> findUserByEmailIgnoreCase(String email);
}
