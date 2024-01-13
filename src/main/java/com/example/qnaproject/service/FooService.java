package com.example.qnaproject.service;

import com.example.qnaproject.entity.Foo;
import com.example.qnaproject.repository.FooRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FooService {
    private final FooRepository fooRepository;

    public void save(){
        fooRepository.save(new Foo("foo"));
    }
}
