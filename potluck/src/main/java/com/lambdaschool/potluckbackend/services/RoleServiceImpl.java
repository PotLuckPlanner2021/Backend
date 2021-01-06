package com.lambdaschool.potluckbackend.services;

import com.lambdaschool.potluckbackend.exceptions.ResourceNotFoundException;
import com.lambdaschool.potluckbackend.models.Role;
import com.lambdaschool.potluckbackend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "roleService")
public class RoleServiceImpl
    implements RoleService
{

    @Autowired
    RoleRepository rolerepos;

    @Override
    public Role findByName(String name)
    {
     Role rr = rolerepos.findByName(name);
     if(rr != null)
     {
         return rr;
     } else
     {
         throw new ResourceNotFoundException(name);
     }
    }
}
