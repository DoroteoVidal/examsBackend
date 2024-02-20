package com.system.exams.systemexamsbackend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.exams.systemexamsbackend.entities.User;

/**
 * Interfaz de la clase Usuario para implementar metodos CRUD y metodos propios de la clase
 * @author Vidal Doroteo
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 
     * @param username nombre de usuario que se utilizara para buscar un Usuario.
     * @return Usuario encontrado.
     */
    public User findByUsername(String username);

    /**
     * 
     * @param email email de usuario que se utilizara para buscar un Usuario.
     * @return Optional<User> encontrado.
     */
    public Optional<User> findUserByEmailIgnoreCase(String email);
}
