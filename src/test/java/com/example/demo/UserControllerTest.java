package com.example.demo;

import com.example.demo.users.UserController;
import com.example.demo.users.UserDto;
import com.example.demo.users.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void getUserTest(){
        List<UserDto> userDtoList = Arrays.asList(
                new UserDto(1L, "TestUser", "test@example.com", "password"),
                new UserDto(2L, "TestUserTwo", "testTwo@example.com", "passwordTwo")
        );
        when(userService.getUsers()).thenReturn(userDtoList);
        userService.getUsers();
        verify(userService, times(1)).getUsers();
        assertEquals(2,userDtoList.size());
    }

}
