package com.system.exams.systemexamsbackend.DTO;

import lombok.Getter;

@Getter
public class DTOUser {
    private String username;
    private String password;
    private String name;
    private String lastname;
    private String email;
    private String phone;
    private String profile;

    public DTOUser(String username, String password, String name, String lastname, String email, String phone, String profile) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.profile = profile;
    }
}
