package com.withgpt.gpt.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name="Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String name;
    private int age;
    private String phoneNumber;
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private Gender gender; // Gender는 ENUM으로 정의

    private String password; // 암호화된 비밀번호
}
