package com.example.demo;

import com.example.demo.users.User;
import com.example.demo.users.UserRepository;
import com.example.demo.users.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiveTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;


    @Test
    public void addUserTest(){
        User user = new User(1, "TestUser", "test@example.com", "password");
        User userAdded = userService.addUser(user);
        verify(userRepository, times(1)).save(user);
        assertNotNull(userAdded);
    }

}
