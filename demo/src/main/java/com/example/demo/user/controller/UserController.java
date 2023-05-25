package com.example.demo.user.controller;

import com.example.demo.user.entity.User;
import com.example.demo.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<User> findUser(@RequestParam Map<String, String> searchParams) {
        if (searchParams.isEmpty()) {
            throw new IllegalArgumentException("Minimum 1 request parameter should not be null empty.");
        }

        Map<String, Object> parameters = new HashMap<>();
        searchParams.forEach(parameters::put);

        User user = userService.findUserByParameters(parameters);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }

        return ResponseEntity.notFound().build();
    }
}
