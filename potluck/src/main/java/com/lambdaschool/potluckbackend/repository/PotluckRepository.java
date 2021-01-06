package com.lambdaschool.potluckbackend.repository;


import com.lambdaschool.potluckbackend.models.Potluck;
import org.springframework.data.repository.CrudRepository;

public interface PotluckRepository
    extends CrudRepository<Potluck, Long>
{

}
