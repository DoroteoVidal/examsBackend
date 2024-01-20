package com.system.exams.systemexamsbackend.services.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.exams.systemexamsbackend.entities.Category;
import com.system.exams.systemexamsbackend.repositories.CategoryRepository;
import com.system.exams.systemexamsbackend.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Long id, Category category) throws Exception {
        try{
            if(categoryRepository.existsById(id)) {
                Category tmp = categoryRepository.findById(id).get();
                tmp.setTitle(category.getTitle());
                tmp.setDescription(category.getDescription());
                categoryRepository.save(tmp);
                
                return tmp;
            }else {
                throw new Exception();
            }
        }catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Set<Category> getAll() {
        return new LinkedHashSet<>(categoryRepository.findAll());
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public boolean delete(Long id) throws Exception {
        try{
            if(categoryRepository.existsById(id)) {
            	categoryRepository.deleteById(id);
                return true;
            }else {
                throw new Exception();
            }
        }catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
}
