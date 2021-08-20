package com.springCrudV2.demo.mapper;

import com.springCrudV2.demo.dto.UserDto;
import com.springCrudV2.demo.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto mapToUserDto(User user) {
        UserDto dto = new UserDto();

        if(user != null) {
            dto.setId(user.getId());
            dto.setName(user.getName());
            return dto;
        } else
            return null;
    }

    public User mapToUserEntity(UserDto dto) {
        User user = new User();

        if(dto != null) {
            user.setId(dto.getId());
            user.setName(dto.getName());
            return user;
        } else
            return null;
    }
}
