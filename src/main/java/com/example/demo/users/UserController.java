package com.example.demo.users;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @GetMapping
    public User getMethod(){
        User user = new User(1,"Jack","Jack@email.com","Password");
        return user;
    }
}
