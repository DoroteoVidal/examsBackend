package com.system.exams.systemexamsbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.exams.systemexamsbackend.entities.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    
}
