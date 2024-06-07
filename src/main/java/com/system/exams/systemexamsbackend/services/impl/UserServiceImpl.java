package com.system.exams.systemexamsbackend.services.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.system.exams.systemexamsbackend.DTO.DTOUser;
import com.system.exams.systemexamsbackend.entities.Role;
import com.system.exams.systemexamsbackend.security.AuthorityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.system.exams.systemexamsbackend.entities.User;
import com.system.exams.systemexamsbackend.entities.UserRole;
import com.system.exams.systemexamsbackend.exceptions.UserFoundException;
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
    public User save(DTOUser dtoUser) throws Exception {
        Optional<User> optionalUser = userRepository.findUserByEmailIgnoreCase(dtoUser.getEmail());
        User localUser;
        if(optionalUser.isPresent()) {
            throw new UserFoundException("This user already exists");
        } else {
            User user = new User();
            user.setProfile("default.png");
            user.setUsername(dtoUser.getUsername());
            user.setPassword(dtoUser.getPassword());
            user.setName(dtoUser.getName());
            user.setLastname(dtoUser.getLastname());
            user.setEmail(dtoUser.getEmail());
            user.setPhone(dtoUser.getPhone());

            Set<UserRole> roles = new HashSet<>();

            Role role = new Role();
            role.setId(2L);
            role.setType(AuthorityConstant.USER);

            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);

            roles.add(userRole);

            String encryptedPass = passwordEncoder.encode(dtoUser.getPassword());
            for(UserRole ur : roles) {
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(roles);
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
