package com.example.qnaproject.repository;

import com.example.qnaproject.entity.Foo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FooRepository extends JpaRepository<Foo, Long>, MyFooRepository {
}
