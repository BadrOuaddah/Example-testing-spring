package com.example.demo.users;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUserDto(User user);
    UserDto toUser(UserDto userDto);
}
