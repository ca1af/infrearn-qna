package com.example.qnaproject.controller;

import com.example.qnaproject.service.FooService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FooController {
    private final FooService fooService;
    @PostMapping("/foo")
    public void save(){
        fooService.save();
    }
}
