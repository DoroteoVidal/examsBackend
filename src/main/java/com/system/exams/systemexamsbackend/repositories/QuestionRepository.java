package com.system.exams.systemexamsbackend.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.exams.systemexamsbackend.entities.Exam;
import com.system.exams.systemexamsbackend.entities.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    
    public Set<Question> findByExam(Exam exam);
}
