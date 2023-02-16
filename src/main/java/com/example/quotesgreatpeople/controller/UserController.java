package com.example.quotesgreatpeople.controller;

import com.example.quotesgreatpeople.dto.UserDto;
import com.example.quotesgreatpeople.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping
    public UserDto createUser(@RequestBody @Valid UserDto user) {
        return userService.createUser(user);
    }
}
