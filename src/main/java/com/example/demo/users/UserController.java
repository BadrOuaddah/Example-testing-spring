package com.example.demo.users;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @GetMapping
    public ArrayList<User> getMethod(){
        ArrayList<User> userList = new ArrayList<>();
        User user_1 = new User(1,"Jack","Jack@email.com","Password");
        User user_2 = new User(2,"John","John@email.com","Password");
        User user_3 = new User(3,"Tommy","Tommy@email.com","Password");
        User user_4 = new User(4,"Jimmy","Jimmy@email.com","Password");
        userList.add(user_1);
        userList.add(user_2);
        userList.add(user_3);
        userList.add(user_4);
        return userList;
    }


}
