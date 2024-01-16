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
    public List<UserDto> getUser(){
        return userService.getUsers();
    }

    @PostMapping(path = "/user")
    public UserDto addNewUser(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }

    @PutMapping(path = "/user/{id}")
    public void updateUser(@RequestBody UserDto userDto,@PathVariable("id") Long id){
        userService.updateUser(userDto, id);
    }

    @DeleteMapping(path = "/user/{id}")
    public UserDto deleteUser(@PathVariable("id") Long id){
        return userService.deleteUser(id);
    }


}
