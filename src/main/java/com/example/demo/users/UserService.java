package com.example.demo.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> getUsers() {
        List<User> userList = userRepository.findAll();
        return userMapper.toUserDtoList(userList);
    }

    public UserDto addUser(UserDto userDto){
        User user = userMapper.toUser(userDto);
        userRepository.save(user);
        return userMapper.toUserDto(user);
    }

    public void updateUser(UserDto userDto, Long id){
        User userOptional = userRepository.findUserById(id);
        if (userOptional != null){
            userOptional.setUserName(userDto.getUserName());
            userOptional.setEmail(userDto.getEmail());
            userOptional.setPassword(userDto.getPassword());
            userRepository.save(userOptional);
        }
        else {
            throw new IllegalArgumentException("User with ID " + id + " not found");
        }
    }

    public UserDto deleteUser(Long id){
        boolean exists = userRepository.existsById(id);
        if (!exists){
            throw  new IllegalArgumentException("User with ID : " + id + " does not exists");
        }
        userRepository.deleteById(id);
        return null;
    }
}
