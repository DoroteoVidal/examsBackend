package com.system.exams.systemexamsbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.exams.systemexamsbackend.entities.Category;
import com.system.exams.systemexamsbackend.entities.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    
    public List<Exam> findByCategory(Category category);

    public List<Exam> findByIsActive(boolean status);

    public List<Exam> findByCategoryAndIsActive(Category category, boolean status);

}
