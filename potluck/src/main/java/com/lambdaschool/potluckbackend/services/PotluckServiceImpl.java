package com.lambdaschool.potluckbackend.services;

import com.lambdaschool.potluckbackend.exceptions.ResourceNotFoundException;
import com.lambdaschool.potluckbackend.models.*;
import com.lambdaschool.potluckbackend.repository.PotluckRepository;
import com.lambdaschool.potluckbackend.repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    UserRepository userrepos;

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
    public Potluck save(long userid, Potluck potluck)
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
        newPotluck.setImgurl(potluck.getImgurl());

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

         User currentUser = userrepos.findById(userid)
             .orElseThrow(() -> new ResourceNotFoundException("User with id " + userid + "not found !"));
          if(currentUser != null){
              newPotluck.setUser(currentUser);
          }
        return potluckrepos.save(newPotluck);
    }

    @Override
    public Potluck update(long potluckid, Potluck replacePotluck)
    {
        Potluck currentPotluck = findPotluckById(potluckid);

        if(replacePotluck.getName() != null)
        {
            currentPotluck.setName(replacePotluck.getName());
        }

        if(replacePotluck.getTime() != null)
        {
            currentPotluck.setTime(replacePotluck.getTime());
        }

        if(replacePotluck.getDate() != null)
        {
            currentPotluck.setDate(replacePotluck.getDate());
        }

        if(replacePotluck.getLocation() != null)
        {
            currentPotluck.setLocation(replacePotluck.getLocation());
        }

        if(replacePotluck.getHost() != null)
        {
            currentPotluck.setHost(replacePotluck.getHost());
        }

        if(replacePotluck.getImgurl() != null)
        {
            currentPotluck.setImgurl(replacePotluck.getImgurl());
        }

        if(replacePotluck.getTheme() != null)
        {
            currentPotluck.setTheme(replacePotluck.getTheme());
        }

        if(replacePotluck.getItems().size() > 0)
        {
            currentPotluck.getItems().clear();

           for(FoodItems foodItem : replacePotluck.getItems())
           {
               currentPotluck.getItems().add(new FoodItems(foodItem.getItemname(),
                   currentPotluck));
           }
        }

        if(replacePotluck.getGuests().size() > 0)
        {
            currentPotluck.getGuests().clear();

            for(Guest guest: replacePotluck.getGuests())
            {
                currentPotluck.getGuests().add(new Guest(guest.getGuestname(),
                    currentPotluck));
            }
        }

        return potluckrepos.save(currentPotluck);

    }

    @Override
    public void delete(long id)
    {
       potluckrepos.findById(id)
           .orElseThrow(() -> new ResourceNotFoundException("Potluck with id " + id + "not found!"));
       potluckrepos.deleteById(id);
    }




}
