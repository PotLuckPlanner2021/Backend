package com.lambdaschool.potluckbackend.services;

import com.lambdaschool.potluckbackend.models.Potluck;
import com.lambdaschool.potluckbackend.models.User;

import java.util.List;

public interface UserService
{


    User  findByUserId(long id);

    User save(User user);

    List<User> findAll();
}
