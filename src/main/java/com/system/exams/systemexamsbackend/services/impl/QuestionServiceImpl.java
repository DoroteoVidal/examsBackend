package com.system.exams.systemexamsbackend.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.exams.systemexamsbackend.entities.Exam;
import com.system.exams.systemexamsbackend.entities.Question;
import com.system.exams.systemexamsbackend.repositories.ExamRepository;
import com.system.exams.systemexamsbackend.repositories.QuestionRepository;
import com.system.exams.systemexamsbackend.services.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ExamRepository examRepository;

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
                tmp.setAnswer(question.getAnswer());
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

    @Override
    public List<Question> listQuestions(Long id) {
        Exam exam = examRepository.findById(id).get();
        if(exam == null) {
            return null;
        }
        
        Set<Question> questions = exam.getQuestions();
        List<Question> exams = new ArrayList<>(questions);
        if(exams.size() > Integer.parseInt(exam.getNumberOfQuestions())) {
            exams = exams.subList(0, Integer.parseInt(exam.getNumberOfQuestions() + 1));
        }
        Collections.shuffle(exams);

        return exams;
    }

    @Override
    public Set<Question> listQuestionsAsAdmin(Long id) {
        Exam exam = new Exam();
        exam.setId(id);
        Set<Question> questions = questionRepository.findByExam(exam);
        if(questions == null) {
            return null;
        }

        return questions;
    }

    @Override
    public Map<String, Object> evaluateExam(List<Question> questions) {
        double maxPoints = 0;
        Integer correctAnswers = 0;
        Integer attempts = 0;

        for(Question p : questions) {
            Question question = questionRepository.findById(p.getId()).get();
            if(question.getCorrectAnswer().equals(p.getAnswer())) {
                correctAnswers++;
                double points = Double.parseDouble(questions.get(0).getExam().getMaxPoints())/questions.size();
                maxPoints += points;
            }
            if(p.getAnswer() != null) {
                attempts++;
            }
        }

        Map<String, Object> answers = new HashMap<>();
        answers.put("maxPoints", maxPoints);
        answers.put("correctAnswers", correctAnswers);
        answers.put("attempts", attempts);

        return answers;
    }
    
}
