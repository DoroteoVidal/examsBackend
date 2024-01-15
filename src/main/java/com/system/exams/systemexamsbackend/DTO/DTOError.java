package com.system.exams.systemexamsbackend.DTO;

import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;

@Getter
public class DTOError {
    private int code;
    private String message;
    private String date;

    public DTOError(int code, String message) {
        this.code = code;
        this.message = message;
        this.date = LocalDateTime.now().toString();
    }
    
    public String toJson() {
        try{
            return new ObjectMapper().writeValueAsString(this);
        }catch(RuntimeException | JsonProcessingException e) {
            return String.format("{message: %s}", this.message);
        }
    }
    
}
