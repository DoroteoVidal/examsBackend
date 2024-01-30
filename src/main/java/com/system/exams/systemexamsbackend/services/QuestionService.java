package com.system.exams.systemexamsbackend.services;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.system.exams.systemexamsbackend.entities.Question;

public interface QuestionService {
    
    public Question save(Question question);

    public Question update(Long id, Question question) throws Exception;

    public Set<Question> getAll();

    public Question getById(Long id);

    public boolean delete(Long id) throws Exception;

    public List<Question> listQuestions(Long id);

    public Set<Question> listQuestionsAsAdmin(Long id);

    public Map<String, Object> evaluateExam(List<Question> questions);

}
