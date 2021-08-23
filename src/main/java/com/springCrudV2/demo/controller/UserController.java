package com.springCrudV2.demo.controller;

import com.springCrudV2.demo.dto.UserDto;
import com.springCrudV2.demo.entity.User;
import com.springCrudV2.demo.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserDetailService userDetailService;

    @Autowired
    public UserController(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('developers:general')")
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> userList = userDetailService.getAll();
        return userList != null
                ? new ResponseEntity<>(userList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "{id}")
    @PreAuthorize("hasAuthority('developers:general')")
    public ResponseEntity<UserDto> getById(@PathVariable("id") @NotNull Long id) {
        UserDto user = userDetailService.getUserById(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('developers:general')")
    public ResponseEntity<UserDto> save(@Valid @NotNull @RequestBody UserDto userDto) {
        UserDto user = userDetailService.save(userDto);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('developers:general')")
    public ResponseEntity<UserDto> update(@Valid  @NotNull @RequestBody UserDto userDto) {
        UserDto user = userDetailService.save(userDto);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "{id}")
    @PreAuthorize("hasAuthority('developers:general')")
    public ResponseEntity<User> deleteById(@PathVariable("id") Long id) {
        userDetailService.deletById(id);
        return id != null
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
