package com.example.qnaproject.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Rollback(value = false)
class FooServiceTest {
    @Autowired
    FooService fooService;

    @Test
    void save() {
        assertDoesNotThrow(() -> fooService.save());
    }
}