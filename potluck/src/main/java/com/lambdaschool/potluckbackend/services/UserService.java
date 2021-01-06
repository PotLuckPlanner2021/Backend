package com.lambdaschool.potluckbackend.services;

import com.lambdaschool.potluckbackend.models.User;

import java.util.List;

public interface UserService
{
    User findByName(String name);

    User save(User user);

    List<User> findAll();
}
