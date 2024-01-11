package com.example.demo.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user){
        userRepository.save(user);
        return user;
    }

    public void updateUser(User user, Long id){
        User userOptional = userRepository.findUserById(id);
        if (userOptional != null){
            userOptional.setUserName(user.getUserName());
            userOptional.setEmail(user.getEmail());
            userOptional.setPassword(user.getPassword());
            userRepository.save(userOptional);
        }
        else {
            throw new IllegalArgumentException("User with ID " + id + " not found");
        }
    }

    public void deleteUser(Long id){
        boolean exists = userRepository.existsById(id);
        if (!exists){
            throw  new IllegalStateException("User with ID : " + id + "does not exists");
        }
        userRepository.findUserById(id);
    }
}
