package com.springCrudV2.demo.service;

import com.springCrudV2.demo.dao.UserRepository;
import com.springCrudV2.demo.exception.LanguageNotFoundException;
import com.springCrudV2.demo.model.UserSecurity;
import com.springCrudV2.demo.service.securityService.UserDetailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDetailServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailService userDetailService;

    @Test
    void shouldThrowExceptionIfUserIsNotExists() {
        //when
        when(userRepository.getUserByName(any())).thenReturn(java.util.Optional.empty());

        //than
        assertThatThrownBy(() -> userDetailService.loadUserByUsername(any()))
                .isInstanceOf(UsernameNotFoundException.class);
    }
}