package com.system.exams.systemexamsbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.exams.systemexamsbackend.entities.Exam;
import com.system.exams.systemexamsbackend.services.ExamService;

@RestController
@RequestMapping("/exams")
@CrossOrigin("*")
public class ExamController {
    
    @Autowired
    private ExamService examService;

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Exam exam) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(examService.save(exam));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. The entity could not be saved, review the data and try again.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(examService.getById(id));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error. Try again later.");
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(examService.getAll());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error. Try again later.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Exam exam) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(examService.update(id, exam));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Could not update, review the data and try again.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(examService.delete(id));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. The entity you are trying to delete does not exist.");
        }
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getCategoryExams(@PathVariable("id") Long id) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(examService.getCategoryExams(id));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error. Try again later.");
        }
    }

    @GetMapping("/active")
    public ResponseEntity<?> getActiveExams() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(examService.getActiveExams());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error. Try again later.");
        }
    }

    @GetMapping("/category/active/{id}")
    public ResponseEntity<?> getActiveExamsOfCategory(@PathVariable("id") Long id) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(examService.getActiveExamsOfCategory(id));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error. Try again later.");
        }
    }
}
