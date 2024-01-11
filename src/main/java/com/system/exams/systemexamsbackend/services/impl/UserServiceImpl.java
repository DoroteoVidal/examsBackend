package com.system.exams.systemexamsbackend.services.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.system.exams.systemexamsbackend.entities.User;
import com.system.exams.systemexamsbackend.entities.UserRole;
import com.system.exams.systemexamsbackend.repositories.RoleRepository;
import com.system.exams.systemexamsbackend.repositories.UserRepository;
import com.system.exams.systemexamsbackend.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(User user, Set<UserRole> userRoles) throws Exception {
        User localUser = userRepository.findByUsername(user.getUsername());
        if(localUser != null) {
            throw new Exception("This user already exists");
        }else {
            String encryptedPass = passwordEncoder.encode(user.getPassword());
            for(UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            user.setPassword(encryptedPass);
            localUser = userRepository.save(user);
        }
        return localUser;
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean delete(Long id) throws Exception {
        try{
            if(userRepository.existsById(id)) {
            	userRepository.deleteById(id);
                return true;
            }else {
                throw new Exception();
            }
        }catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
}
