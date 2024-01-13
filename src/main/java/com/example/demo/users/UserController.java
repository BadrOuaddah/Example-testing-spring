package com.example.demo.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUser(){
        return userService.getUsers();
    }

    @PostMapping(path = "/user")
    public User addNewUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PutMapping(path = "/user/{id}")
    public void updateUser(@RequestBody User user,@PathVariable("id") Long id){
        userService.updateUser(user, id);
    }

    @DeleteMapping(path = "/user/{id}")
    public User deleteUser(@PathVariable("id") Long id){
        return userService.deleteUser(id);
    }


}
