package com.lambdaschool.potluckbackend.controllers;


import com.lambdaschool.potluckbackend.models.User;
import com.lambdaschool.potluckbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private UserService userService;

    @GetMapping(value = "/users", produces = "application/json")
    public ResponseEntity<?> listAllUsers() {
        List<User> myUsers = userService.findAll();

        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }

    @PostMapping(value = "/user", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewUser(@Valid @RequestBody User newUser)
        throws URISyntaxException
    {
        newUser.setUserid(0);
       newUser = userService.save(newUser);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserUri = ServletUriComponentsBuilder.fromCurrentRequestUri()
            .path("/{userid}")
            .buildAndExpand(newUser.getUserid())
            .toUri();
        responseHeaders.setLocation(newUserUri);


        return new ResponseEntity<>(null, responseHeaders, HttpStatus.OK);
    }
}
