package com.springCrudV2.demo.config;

import com.springCrudV2.demo.entity.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .anyRequest().authenticated();
    }

    @Bean
    protected UserDetailsService users() {
        return new InMemoryUserDetailsManager(
        User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .authorities(Role.ADMIN.getAuthorities())
                .build(),
        User.builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .authorities(Role.USER.getAuthorities())
                .build()
        );
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}