package com.springCrudV2.demo.service;

import com.springCrudV2.demo.dao.UserRepository;
import com.springCrudV2.demo.dto.UserDto;
import com.springCrudV2.demo.entity.User;
import com.springCrudV2.demo.exception.UserNotFoundException;
import com.springCrudV2.demo.mapper.UserMapper;
import com.springCrudV2.demo.security.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("userDetailServiceImpl")
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserDetailService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.getUserByName(name).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
        return UserSecurity.fromUser(user);
    }

    public List<UserDto> getAll() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(userMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return userMapper.mapToUserDto(user);
    }

    public UserDto save(UserDto dto) {
        isValidDto(dto);
        User user = fillFieldUser(dto);
        User saveUser = userRepository.save(user);
        dto.setId(saveUser.getId());
        return dto;
    }

    public void deletById(Long id) {
        userRepository.deleteById(id);
    }

    public User fillFieldUser(UserDto dto) {
        isValidDto(dto);
        User user = userMapper.mapToUserEntity(dto);

        user.setCity(userRepository.getUserByName(dto.getName()).get().getCity());
        user.setPassword(userRepository.getUserByName(dto.getName()).get().getPassword());
        user.setRole(userRepository.getUserByName(dto.getName()).get().getRole());
        return user;
    }

    public boolean isValidDto(UserDto dto) {
        if(!userRepository.existsById(dto.getId()))
            throw new UserNotFoundException(dto.getId());
        return true;
    }



}
