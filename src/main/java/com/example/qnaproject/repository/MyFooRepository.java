package com.example.qnaproject.repository;

import com.example.qnaproject.entity.Foo;

import java.util.List;

public interface MyFooRepository {
    List<Foo> findAll();
}
