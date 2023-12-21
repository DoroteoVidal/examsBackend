package com.system.exams.systemexamsbackend.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.system.exams.systemexamsbackend.entities.Role;
import com.system.exams.systemexamsbackend.entities.User;
import com.system.exams.systemexamsbackend.entities.UserRole;
import com.system.exams.systemexamsbackend.services.UserService;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    /**
     * 
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody User user) throws Exception {
        try{
            Set<UserRole> roles = new HashSet<>();
            Role role = new Role();
            role.setId(2L);
            role.setType("NORMAL");
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user, roles));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Could not save user.\"}");
        }
    }

    /**
     * 
     * @param username
     * @return
     * @throws Exception
     */
    @GetMapping("/{username}")
    public ResponseEntity<?> getByUsername(@PathVariable("username") String username) throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.getByUsername(username));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. There is no user with this username.\"}");
        }
    }
    
    /**
     * 
     * @param id
     * @return
     * @throws Exception
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userService.delete(id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. The user you are trying to delete does not exist.\"}");
        }
    }
}
