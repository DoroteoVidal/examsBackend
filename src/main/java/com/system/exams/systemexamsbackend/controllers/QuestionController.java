package com.system.exams.systemexamsbackend.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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
import com.system.exams.systemexamsbackend.entities.Question;
import com.system.exams.systemexamsbackend.services.ExamService;
import com.system.exams.systemexamsbackend.services.QuestionService;

@RestController
@RequestMapping("/questions")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ExamService examService;

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Question question) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(questionService.save(question));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. The entity could not be saved, review the data and try again.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(questionService.getById(id));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error. Try again later.");
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(questionService.getAll());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error. Try again later.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Question question) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(questionService.update(id, question));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Could not update, review the data and try again.");
        }
    }

    @GetMapping("/exam/{id}")
    public ResponseEntity<?> listQuestions(@PathVariable("id") Long id) {
        try{
            Exam exam = examService.getById(id);
            Set<Question> questions = exam.getQuestions();
            List<Question> exams = new ArrayList<>(questions);
            if(exams.size() > Integer.parseInt(exam.getNumberOfQuestions())) {
                exams = exams.subList(0, Integer.parseInt(exam.getNumberOfQuestions() + 1));
            }
            Collections.shuffle(exams);

            return ResponseEntity.status(HttpStatus.OK).body(exams);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error. Try again later.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(questionService.delete(id));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. The entity you are trying to delete does not exist.");
        }
    }
    
}
