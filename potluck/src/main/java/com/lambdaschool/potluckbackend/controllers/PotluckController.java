package com.lambdaschool.potluckbackend.controllers;


import com.lambdaschool.potluckbackend.models.Potluck;
import com.lambdaschool.potluckbackend.models.User;
import com.lambdaschool.potluckbackend.repository.PotluckRepository;
import com.lambdaschool.potluckbackend.services.PotluckService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/potlucks")
public class PotluckController
{
    @Autowired
    PotluckService potluckService;

    @Autowired
    PotluckRepository potluckrepos;

    // localhost:2019/potlucks/potlucks

    @GetMapping(value = "/potlucks", produces = "application/json")
    public ResponseEntity<?> listAllPotlucks() {
        List<Potluck> potlucks = potluckService.findAll();

        return new ResponseEntity<>(potlucks, HttpStatus.OK);
    }

    // localhost:2019/potlucks/potlucks/1
    @GetMapping(value = "/potlucks/{potluckid}", produces = "application/json")
    public ResponseEntity<?> listPotluckById(@PathVariable long potluckid) {
        Potluck p = potluckService.findPotluckById(potluckid);

        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @PostMapping(value = "/{userid}/potlucks", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewUserPotluck(@PathVariable long userid,
                                               @Valid
                                              @RequestBody
                                               Potluck
                                               newPotluck)
    {
            newPotluck = potluckService.save(userid, newPotluck);

            // location header for the newly created potluck

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPotluckURI = ServletUriComponentsBuilder.fromCurrentRequestUri()
            .path("/{potluckid}")
            .buildAndExpand(newPotluck.getPotluckid())
            .toUri();
        responseHeaders.setLocation(newPotluckURI);
            return new ResponseEntity<>(newPotluck, responseHeaders, HttpStatus.CREATED);
    }


    @PutMapping(value = "/potluck/{potluckid}", consumes = {"application/json"})
    public ResponseEntity<?> replacePotluck(@PathVariable long potluckid,
                                            @RequestBody
                                            Potluck
                                            newPotluck){
        newPotluck.setPotluckid(potluckid);
        Potluck currentPotluck = potluckService.update(potluckid, newPotluck);

        return new ResponseEntity<>(currentPotluck, HttpStatus.OK);
    }

    @DeleteMapping(value = "/potluck/{potluckid}")
    public ResponseEntity<?> DeletePotluckById(@PathVariable long potluckid){
       potluckService.delete(potluckid);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
