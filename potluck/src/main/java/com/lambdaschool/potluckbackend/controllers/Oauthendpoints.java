package com.lambdaschool.potluckbackend.controllers;


import com.lambdaschool.potluckbackend.models.User;
import com.lambdaschool.potluckbackend.models.UserMinimum;
import com.lambdaschool.potluckbackend.models.UserRoles;
import com.lambdaschool.potluckbackend.services.RoleService;
import com.lambdaschool.potluckbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class allows access to endpoints that are open regardless of their authentication status
 */

@RestController
public class Oauthendpoints
{

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PostMapping(value = "/createnewuser",
                consumes = "application/json",
                produces = "application/json")
    public ResponseEntity<?> addSelf(
        HttpServletRequest httpServletRequest,
        @Valid
        @RequestBody
            UserMinimum newminuser)
        throws URISyntaxException
    {
        User newuser = new User();

        newuser.setUsername(newminuser.getPassword());
        newuser.setPassword(newminuser.getPassword());
        newuser.setPrimaryemail(newminuser.getPrimaryemail());

        //add the default role of a user
        Set<UserRoles> newRoles = new HashSet<>();
        newRoles.add(new UserRoles(newuser,
            roleService.findByName("ORGANIZER")));
        newuser.setRoles(newRoles);

        newuser = userService.save(newuser);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromUriString(httpServletRequest.getServerName() + ":" + httpServletRequest.getLocalPort() + "/users/user/{userId}")
            .buildAndExpand(newuser.getUserid())
            .toUri();
        responseHeaders.setLocation(newUserURI);

        //RETURN THE ACCESS TOKEN
        // To get the access token, surf to the endpoint /login ( which is always on the server that it is running)
        // Just as if a client had done this.

        RestTemplate restTemplate = new RestTemplate();
        String requestURI = "http://localhost" +  ":" + httpServletRequest.getLocalPort() + "/login";

        List<MediaType> acceptableMediaTypes = new ArrayList<>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(acceptableMediaTypes);

        headers.setBasicAuth("lambda-client", "lambda-secret");

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        map.add("grant-type",
            "password");
        map.add("scope",
            "read write trust");
        map.add("username",
            newminuser.getUsername());
        map.add("password",
            newminuser.getPassword());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map,
            headers);

        String theToken = restTemplate.postForObject(requestURI,
            request,
            String.class);

        return new ResponseEntity<>(theToken,
            responseHeaders,
            HttpStatus.CREATED);
    }
}
