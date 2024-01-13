package com.example.qnaproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Foo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isDeleted; // 질문자님께서 선언하신 deleted 필드와 같음
    private String someField;

    public Foo(String someField) {
        this.someField = someField;
        this.isDeleted = false;
    }

    public void delete(){
        this.isDeleted = true;
    }
}