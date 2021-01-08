package com.lambdaschool.potluckbackend.services;

import com.lambdaschool.potluckbackend.PotluckbackendApplicationTest;
import com.lambdaschool.potluckbackend.models.Role;
import com.lambdaschool.potluckbackend.models.User;
import com.lambdaschool.potluckbackend.models.UserRoles;
import com.lambdaschool.potluckbackend.repository.RoleRepository;
import com.lambdaschool.potluckbackend.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PotluckbackendApplicationTest.class,
    properties = {
    "command.line.runner.enabled=false"
    })
public class UserServiceImplUnitNoDbTest
{
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userrepos;

    @MockBean
    private RoleRepository rolerepos;

    private List<User> userList = new ArrayList<>();

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        // Adding SeedData for users
        Role r1 = new Role("ORGANIZER");
        Role r2 = new Role("GUEST");
        r1.setRoleid(1);
        r2.setRoleid(2);

        // admin
        ArrayList<UserRoles> org = new ArrayList<>();
        org.add(new UserRoles(new User(), r1));
        User u1 = new User("testsometestuser",
            "password",
            "testemail1@email.local");
        u1.setUserid(11);
        u1.getRoles()
            .add(new UserRoles(u1, r1));

        userList.add(u1);
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void findByUserId()
    {
        Mockito.when(userrepos.findById(10L))
        .thenReturn(Optional.of(userList.get(0)));

            assertEquals("testsometestuser",
                userService.findByUserId(10L).getUsername());
    }

    @Test
    public void save()
    {
        Role r1 = new Role("ORGANIZER");
        Role r2 = new Role("GUEST");
        r1.setRoleid(1);
        r2.setRoleid(2);

        // admin
        User u1 = new User();
        u1.setUsername("testuser");
        u1.setPassword("12345");
        u1.setPrimaryemail("somemailtest@gmail.com");
        u1.getRoles()
            .add(new UserRoles(u1, r1));

      Mockito.when(userrepos.save(any(User.class)))
          .thenReturn(u1);

      Mockito.when(rolerepos.findById(1L))
      .thenReturn(Optional.of(r1));

      User addUser = userService.save(u1);
      assertNotNull(addUser);
      assertEquals(u1.getUsername(),
          addUser.getUsername());

    }

    @Test
    public void findAll()
    {
       Mockito.when(userrepos.findAll())
       .thenReturn(userList);

       assertEquals(1, userService.findAll().size());
    }
}