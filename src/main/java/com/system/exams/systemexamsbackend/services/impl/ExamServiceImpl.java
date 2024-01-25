package com.system.exams.systemexamsbackend.services.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.exams.systemexamsbackend.entities.Category;
import com.system.exams.systemexamsbackend.entities.Exam;
import com.system.exams.systemexamsbackend.repositories.ExamRepository;
import com.system.exams.systemexamsbackend.services.ExamService;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Override
    public Exam save(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public Exam update(Long id, Exam exam) throws Exception {
        try{
            if(examRepository.existsById(id)) {
                Exam tmp = examRepository.findById(id).get();
                tmp.setTitle(exam.getTitle());
                tmp.setDescription(exam.getDescription());
                tmp.setMaxPoints(exam.getMaxPoints());
                tmp.setNumberOfQuestions(exam.getNumberOfQuestions());
                tmp.setActive(exam.isActive());
                tmp.setCategory(exam.getCategory());
                examRepository.save(tmp);
                
                return tmp;
            }else {
                throw new Exception();
            }
        }catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Set<Exam> getAll() {
        return new LinkedHashSet<>(examRepository.findAll());
    }

    @Override
    public Exam getById(Long id) {
        return examRepository.findById(id).get();
    }

    @Override
    public boolean delete(Long id) throws Exception {
        try{
            if(examRepository.existsById(id)) {
            	examRepository.deleteById(id);
                return true;
            }else {
                throw new Exception();
            }
        }catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Exam> getCategoryExams(Long id) {
        Category category = new Category();
        category.setId(id);
        List<Exam> exams = examRepository.findByCategory(category);
        if(exams == null) {
            return null;
        }

        return exams;
    }

    @Override
    public List<Exam> getActiveExams() {
        return examRepository.findByIsActive(true);
    }

    @Override
    public List<Exam> getActiveExamsOfCategory(Long id) {
        Category category = new Category();
        category.setId(id);
        List<Exam> exams = examRepository.findByCategoryAndIsActive(category, true);
        if(exams == null) {
            return null;
        }

        return exams;
    }
    
}
