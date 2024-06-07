package com.system.exams.systemexamsbackend.services;

import java.util.Set;

import com.system.exams.systemexamsbackend.DTO.DTOUser;
import com.system.exams.systemexamsbackend.entities.User;

public interface UserService {

    User save(DTOUser dtoUser) throws Exception;

    User getByUsername(String username);

    boolean delete(Long id) throws Exception;
}
