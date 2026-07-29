package com.entry.dexam.domain.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entry.dexam.global.exception.exceptions.ExamRangeNotFoundException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	@GetMapping()
    public String sayHello() {
		throw ExamRangeNotFoundException.EXCEPTION;
    }
}
