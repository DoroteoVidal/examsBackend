package com.system.exams.systemexamsbackend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 5000)
    private String content;

    private String image;

    private String option1;
    private String option2;
    private String option3;
    private String option4;

    private String response;

    @ManyToOne(fetch = FetchType.EAGER)
    private Exam exam;

    public Question() {}
    
}
