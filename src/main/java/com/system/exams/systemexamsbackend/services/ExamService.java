package com.system.exams.systemexamsbackend.services;

import java.util.List;
import java.util.Set;

import com.system.exams.systemexamsbackend.entities.Exam;

public interface ExamService {
    
    Exam save(Exam exam);

    Exam update(Long id, Exam exam) throws Exception;

    Set<Exam> getAll();

    Exam getById(Long id);

    boolean delete(Long id) throws Exception;

    List<Exam> getCategoryExams(Long id);

    List<Exam> getActiveExams();

    List<Exam> getActiveExamsOfCategory(Long id);

}
