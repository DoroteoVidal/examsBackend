package com.system.exams.systemexamsbackend.services;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.system.exams.systemexamsbackend.entities.Question;

public interface QuestionService {
    
    Question save(Question question);

    Question update(Long id, Question question) throws Exception;

    Set<Question> getAll();

    Question getById(Long id);

    boolean delete(Long id) throws Exception;

    List<Question> listQuestions(Long id);

    Set<Question> listQuestionsAsAdmin(Long id);

    Map<String, Object> evaluateExam(List<Question> questions);

}
