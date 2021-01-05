package com.lambdaschool.potluckbackend.repository;

import com.lambdaschool.potluckbackend.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
   User  findByUsername(String name);
}
