package com.example.demo;

import com.example.demo.users.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiveTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;


    @Test
    public void addUserTest(){
        User user = new User(1L, "TestUser", "test@example.com", "password");
        UserDto userDto = new UserDto(1L, "TestUser", "test@example.com", "password");
        when(userMapper.toUser(userDto)).thenReturn(user);
        when(userMapper.toUserDto(user)).thenReturn(userDto);
        UserDto userAdded = userService.addUser(userDto);
        assertEquals(userDto,userAdded);
        verify(userRepository, times(1)).save(user);
        verify(userMapper, times(1)).toUser(userDto);
        verify(userMapper, times(1)).toUserDto(user);
    }

    @Test
    public void updateUserTestIfIdFound(){
        Long id = 1L;
        User user = new User(1L,"nameExample","example@email.com","passwordExample");
        UserDto userDtoUpdated = new UserDto(1L,"updateName","update@email.com","updatePassword");
        when(userRepository.findUserById(id)).thenReturn(user);
        userService.updateUser(userDtoUpdated, id);
//        doNothing().when(userRepository.save(any()));
        verify(userRepository, times(1)).save(user);
        // TODO :
    }

    @Test
    public void updateUserTestIfIdNotFound(){
        Long id = 1L;
        UserDto userUpdated = new UserDto(2L,"updateName","update@email.com","updatePassword");
        when(userRepository.findUserById(id)).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> userService.updateUser(userUpdated, id));
    }

    @Test
    public void deleteUserTest(){
        Long id = 1L;
        when(userRepository.existsById(id)).thenReturn(true);
        UserDto userDeleted = userService.deleteUser(id);
        verify(userRepository, times(1)).deleteById(id);
        assertNull(userDeleted);
    }

    @Test
    public void deleteUserTestIfIdNotFound(){
        Long id = 1L;
        when(userRepository.existsById(id)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> userService.deleteUser(id));
        verify(userRepository,never()).deleteById(id);
    }

}
