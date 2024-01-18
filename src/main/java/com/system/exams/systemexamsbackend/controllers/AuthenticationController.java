package com.system.exams.systemexamsbackend.controllers;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.system.exams.systemexamsbackend.DTO.DTOAuth;
import com.system.exams.systemexamsbackend.entities.Role;
import com.system.exams.systemexamsbackend.entities.User;
import com.system.exams.systemexamsbackend.entities.UserRole;
import com.system.exams.systemexamsbackend.security.AuthorityConstant;
import com.system.exams.systemexamsbackend.security.DomainUserDetailsService;
import com.system.exams.systemexamsbackend.security.JwtFilter;
import com.system.exams.systemexamsbackend.security.TokenProvider;
import com.system.exams.systemexamsbackend.services.UserService;

import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin("*")
public class AuthenticationController {
    
    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private UserService userService;

    @Autowired
    private DomainUserDetailsService domainUserDetailsService;

    @GetMapping("/actual-user")
    public User getActualUser(Principal principal) {
        return (User) this.domainUserDetailsService.loadUserByEmail(principal.getName());
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtToken> authenticate(@Valid @RequestBody DTOAuth a){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(a.getEmail(), a.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final var jwt = tokenProvider.createToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new JwtToken(jwt), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@Valid @RequestBody User user) throws Exception {
        try{
            user.setProfile("default.png");
            Set<UserRole> roles = new HashSet<>();

            Role role = new Role();
            role.setId(2L);
            role.setType(AuthorityConstant.USER);

            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);

            roles.add(userRole);
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user, roles));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Could not save user.\"}");
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<DTOValidateToken> validate() {
        final var user = SecurityContextHolder.getContext().getAuthentication();
        String authority = String.valueOf(user.getAuthorities().stream().findFirst());

        return ResponseEntity.ok(
                DTOValidateToken.builder()
                        .username(user.getName())
                        .authorities(authority)
                        .isAuthenticated(true)
                        .build()
        );
    }
    
    @Data
    @Builder
    public static class DTOValidateToken {
        private boolean isAuthenticated;
        private String username;
        private String authorities;
    }

    static class JwtToken {
        private String idToken;

        JwtToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }

}
