package com.example.demo.users;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserDto userDto);
    UserDto toUserDto(User user);
}
