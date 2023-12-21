package com.system.exams.systemexamsbackend.services;

import java.util.Set;

import com.system.exams.systemexamsbackend.entities.User;
import com.system.exams.systemexamsbackend.entities.UserRole;

/**
 * 
 */
public interface UserService {
    
    /**
     * 
     * @param user
     * @param userRoles
     * @return
     * @throws Exception
     */
    public User save(User user, Set<UserRole> userRoles) throws Exception;

    /**
     * 
     * @param username
     * @return
     */
    public User getByUsername(String username);

    /**
     * 
     * @param id
     * @return
     * @throws Exception
     */
    public boolean delete(Long id) throws Exception;
}
