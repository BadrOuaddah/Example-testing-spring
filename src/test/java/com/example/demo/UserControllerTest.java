package com.example.demo;

import com.example.demo.users.User;
import com.example.demo.users.UserController;
import com.example.demo.users.UserDto;
import com.example.demo.users.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getUserTest() throws Exception {
        List<UserDto> userDtoList = Arrays.asList(
                new UserDto(1L, "TestUser", "test@example.com", "password"),
                new UserDto(2L, "TestUserTwo", "testTwo@example.com", "passwordTwo")
        );
        when(userService.getUsers()).thenReturn(userDtoList);
        userController.getUser();
        verify(userService, times(1)).getUsers();
        assertEquals(2,userDtoList.size());
        mockMvc.perform(get("/api/v1/users").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void addNewUserTest() throws Exception {
        UserDto userDto = new UserDto(1L, "TestUser", "test@example.com", "password");
        when(userService.addUser(userDto)).thenReturn(userDto);
        userController.addNewUser(userDto);
        verify(userService,times(1)).addUser(userDto);
        mockMvc.perform(post("/api/v1/users/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateUserTest() throws Exception {
        Long id = 1L;
        User user = new User();
        UserDto userUpdated = new UserDto(id, "UserUpdated", "updated@example.com", "passwordUpdated");
          mockMvc.perform(put("/api/v1/users/user/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userUpdated)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUserTest(){

    }

}
