package com.lambdaschool.potluckbackend.repository;

import com.lambdaschool.potluckbackend.models.Potluck;
import com.lambdaschool.potluckbackend.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Long>
{
   User  findByUsername(String name);

   @Query(value = "SELECT * FROM POTLUCKS\n" +
       "JOIN USER u\n" +
       "WHERE u.userid = :userid\n", nativeQuery = true)
   List<Potluck> findPotlucksByUserid(long userid);
}
