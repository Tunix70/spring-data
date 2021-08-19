package com.springCrudV2.demo.security;

import com.springCrudV2.demo.dao.UserRepository;
import com.springCrudV2.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.getUserByName(name).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
        return UserSecurity.fromUser(user);
    }
}
