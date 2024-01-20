package com.system.exams.systemexamsbackend.services;

import java.util.Set;

import com.system.exams.systemexamsbackend.entities.Exam;
import com.system.exams.systemexamsbackend.entities.Question;

public interface QuestionService {
    
    public Question save(Question question);

    public Question update(Long id, Question question) throws Exception;

    public Set<Question> getAll();

    public Question getById(Long id);

    public Set<Question> getExamQuestions(Exam exam);

    public boolean delete(Long id) throws Exception;
}
