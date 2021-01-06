package com.lambdaschool.potluckbackend.services;

import com.lambdaschool.potluckbackend.exceptions.ResourceNotFoundException;
import com.lambdaschool.potluckbackend.models.Role;
import com.lambdaschool.potluckbackend.models.User;
import com.lambdaschool.potluckbackend.models.UserRoles;
import com.lambdaschool.potluckbackend.repository.RoleRepository;
import com.lambdaschool.potluckbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServiceImpl
    implements UserService
{
    @Autowired
    private UserRepository userrepos;

    @Autowired
    private RoleRepository rolerepos;

    @Override
    public User findByName(String name)
    {
        User user = userrepos.findByUsername(name);
        if(user == null)
        {
            throw new ResourceNotFoundException("User name " + name + " not found!");

        }
        return user;
    }

    @Override
    public User save(User user)
        throws ResourceNotFoundException
    {
        User newUser = new User();

        if (user.getUserid() != 0)
        {
            userrepos.findById(user.getUserid())
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + user.getUserid() + "not found !"));
            newUser.setUserid(user.getUserid());
        }
        newUser.setUsername(user.getUsername()
        .toLowerCase());
        newUser.setPasswordNoEncrypt(user.getPassword());
        newUser.setPrimaryemail(user.getPrimaryemail());
            newUser.getRoles()
                .clear();

            for(UserRoles ur : user.getRoles())
            {
                Role addRole = rolerepos.findById(ur.getRole()
                    .getRoleid())
                    .orElseThrow(() -> new ResourceNotFoundException("Role id " + ur.getRole().getRoleid() + "not found!"));
                newUser.getRoles().add(new UserRoles(newUser, addRole));
            }

            return userrepos.save(newUser);
        }

    @Override
    public List<User> findAll()
    {
        List<User> list = new ArrayList<>();

        userrepos.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;
    }
}
