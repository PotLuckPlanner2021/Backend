package com.lambdaschool.potluckbackend.controllers;


import com.lambdaschool.potluckbackend.models.Potluck;
import com.lambdaschool.potluckbackend.models.User;
import com.lambdaschool.potluckbackend.repository.PotluckRepository;
import com.lambdaschool.potluckbackend.services.PotluckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = )



}
