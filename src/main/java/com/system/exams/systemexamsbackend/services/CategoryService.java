package com.system.exams.systemexamsbackend.services;

import java.util.Set;

import com.system.exams.systemexamsbackend.entities.Category;

public interface CategoryService {
    
    Category save(Category category);

    Category update(Long id, Category category) throws Exception;

    Set<Category> getAll();

    Category getById(Long id);

    boolean delete(Long id) throws Exception;
}
