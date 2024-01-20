package com.system.exams.systemexamsbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.exams.systemexamsbackend.entities.Category;
import com.system.exams.systemexamsbackend.services.CategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/categories")
@CrossOrigin("*")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Category category) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(category));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. The entity could not be saved, review the data and try again.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(categoryService.getById(id));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error. Try again later.");
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAll());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error. Try again later.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Category category) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.update(id, category));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Could not update, review the data and try again.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(categoryService.delete(id));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. The entity you are trying to delete does not exist.");
        }
    }
    
}
