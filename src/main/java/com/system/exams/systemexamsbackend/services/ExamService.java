package com.system.exams.systemexamsbackend.services;

import java.util.Set;

import com.system.exams.systemexamsbackend.entities.Exam;

public interface ExamService {
    
    public Exam save(Exam exam);

    public Exam update(Long id, Exam exam) throws Exception;

    public Set<Exam> getAll();

    public Exam getById(Long id);

    public boolean delete(Long id) throws Exception;
}
