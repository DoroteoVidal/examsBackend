package com.system.exams.systemexamsbackend.services.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.exams.systemexamsbackend.entities.Exam;
import com.system.exams.systemexamsbackend.entities.Question;
import com.system.exams.systemexamsbackend.repositories.QuestionRepository;
import com.system.exams.systemexamsbackend.services.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question update(Long id, Question question) throws Exception {
        try{
            if(questionRepository.existsById(id)) {
                Question tmp = questionRepository.findById(id).get();
                tmp.setContent(question.getContent());
                tmp.setImage(question.getImage());
                tmp.setOption1(question.getOption1());              
                tmp.setOption2(question.getOption2());
                tmp.setOption3(question.getOption3());
                tmp.setOption4(question.getOption4());
                tmp.setResponse(question.getResponse());
                tmp.setExam(question.getExam());
                questionRepository.save(tmp);
                
                return tmp;
            }else {
                throw new Exception();
            }
        }catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Set<Question> getAll() {
        return new LinkedHashSet<>(questionRepository.findAll());
    }

    @Override
    public Question getById(Long id) {
        return questionRepository.findById(id).get();
    }

    @Override
    public Set<Question> getExamQuestions(Exam exam) {
        return questionRepository.findByExam(exam);
    }

    @Override
    public boolean delete(Long id) throws Exception {
        try{
            if(questionRepository.existsById(id)) {
            	questionRepository.deleteById(id);
                return true;
            }else {
                throw new Exception();
            }
        }catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
}
