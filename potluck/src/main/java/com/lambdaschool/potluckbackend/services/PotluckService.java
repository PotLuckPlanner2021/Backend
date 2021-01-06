package com.lambdaschool.potluckbackend.services;

import com.lambdaschool.potluckbackend.models.Potluck;
import com.lambdaschool.potluckbackend.models.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface PotluckService
{
     Potluck findPotluckById(long potluckid);

    List<Potluck> findAll();

    Potluck save(long userid, Potluck newpotluck);

    Potluck update(long potluckid, Potluck newpotluck);

    void delete(long id);
}
