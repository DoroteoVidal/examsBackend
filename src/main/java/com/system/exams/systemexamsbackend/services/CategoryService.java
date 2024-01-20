package com.system.exams.systemexamsbackend.services;

import java.util.Set;

import com.system.exams.systemexamsbackend.entities.Category;

public interface CategoryService {
    
    public Category save(Category category);

    public Category update(Long id, Category category) throws Exception;

    public Set<Category> getAll();

    public Category getById(Long id);

    public boolean delete(Long id) throws Exception;
}
