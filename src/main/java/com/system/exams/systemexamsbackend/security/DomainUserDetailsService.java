package com.system.exams.systemexamsbackend.security;

import com.system.exams.systemexamsbackend.entities.Role;
import com.system.exams.systemexamsbackend.entities.User;
import com.system.exams.systemexamsbackend.entities.UserRole;
import com.system.exams.systemexamsbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DomainUserDetailsService implements UserDetailsService {
    
    private final UserRepository userRepository;

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findUserByEmailIgnoreCase(email)
            .orElseThrow(() -> new UsernameNotFoundException("There is no user with email " + email));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findUserByEmailIgnoreCase(email)
                .map(this::createSpringSecurityUser)
                .orElseThrow(() -> new UsernameNotFoundException("There is no user with email " + email));
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(User user){
       List<GrantedAuthority> grantedAuthorities = user
                .getUserRoles()
                .stream()
                .map(UserRole::getRole)
                .map(Role::getType)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }

}
