package com.lambdaschool.potluckbackend.services;

import com.lambdaschool.potluckbackend.exceptions.ResourceNotFoundException;
import com.lambdaschool.potluckbackend.models.*;
import com.lambdaschool.potluckbackend.repository.PotluckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Transactional
@Service(value = "potluckService")

public class PotluckServiceImpl
    implements PotluckService
{

    @Autowired
    PotluckRepository potluckrepos;

    @Override
    public Potluck findPotluckById(long id) throws
                                      ResourceNotFoundException
    {
        return potluckrepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Potluck with id " + id + " not found!"));
    }

    @Override
    public List<Potluck> findAll()
    {
        List<Potluck> list = new ArrayList<>();

        potluckrepos.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;
    }


    @Override
    public Potluck save(Potluck potluck)
        throws ResourceNotFoundException
    {
        Potluck newPotluck = new Potluck();

        if (potluck.getPotluckid() != 0)
        {
            potluckrepos.findById(potluck.getPotluckid())
                .orElseThrow(() -> new ResourceNotFoundException("PotLuck with id " + potluck.getPotluckid() + "not found !"));
            newPotluck.setPotluckid(potluck.getPotluckid());
        }
        newPotluck.setName(potluck.getName());
        newPotluck.setTime(potluck.getTime());
        newPotluck.setDate(potluck.getDate());
        newPotluck.setLocation(potluck.getLocation());
        newPotluck.setTheme(potluck.getTheme());
        newPotluck.setHost(potluck.getHost());

        newPotluck.getGuests()
            .clear();

        for(Guest guest : potluck.getGuests())
        {
            newPotluck.getGuests()
                .add(new Guest(guest.getGuestname(),
                    newPotluck));
        }
        newPotluck.getItems()
            .clear();
        for(FoodItems item : potluck.getItems())
        {
            newPotluck.getItems()
                .add(new FoodItems(item.getItemname(),
                    newPotluck));
        }


        return potluckrepos.save(newPotluck);
    }


}
