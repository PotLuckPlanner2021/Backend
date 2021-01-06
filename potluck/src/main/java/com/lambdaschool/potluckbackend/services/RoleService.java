package com.lambdaschool.potluckbackend.services;

import com.lambdaschool.potluckbackend.models.Role;

public interface RoleService
{
    Role findByName(String name);
}
