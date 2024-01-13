package com.example.demo.users;

import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserDto userDto);
    UserDto toUserDto(User user);
    List<User> toUserList(List<UserDto> userDtoList);
    List<UserDto> toUserDtoList(List<User> userList);
}
