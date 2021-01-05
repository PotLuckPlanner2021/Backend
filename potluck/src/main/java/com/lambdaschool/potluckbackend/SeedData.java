package com.lambdaschool.potluckbackend;

import com.lambdaschool.potluckbackend.models.*;
import com.lambdaschool.potluckbackend.repository.RoleRepository;
import com.lambdaschool.potluckbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Component
public class SeedData
        implements CommandLineRunner
{

    @Autowired
    private UserRepository userrepos;

    @Autowired
    private RoleRepository rolerepos;

    @Override
    public void run(String... args) throws
            Exception
    {

        // Adding SeedData for users
        Role r1 = new Role("ORGANIZER");
        Role r2 = new Role("GUEST");
        r1 = rolerepos.save(r1);
        r2 = rolerepos.save(r2);

        // admin
        ArrayList<UserRoles> organizer = new ArrayList<>();
        organizer.add(new UserRoles(new User(), r1));
        User u1 = new User("sometestuser", "password");
        userrepos.save(u1);

        // we need to start a new list of roles for the new admin user. For each user, a new list of roles needs to be created
        // The list of UserRoles though is never the same!
        organizer = new ArrayList<>();
        organizer.add(new UserRoles(new User(), r1));
        User u2 = new User("admin", "password");
        userrepos.save(u2);

        // users
        ArrayList<UserRoles> guests = new ArrayList<>();
        guests.add(new UserRoles(new User(), r2));
        User u3 = new User("justuser", "userpass");
        userrepos.save(u3);
    }
}
