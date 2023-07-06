package com.nikki.webnews.service;

import com.nikki.webnews.dto.UserDto;
import com.nikki.webnews.model.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
