package com.system.exams.systemexamsbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.exams.systemexamsbackend.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {}
