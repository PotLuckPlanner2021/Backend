package com.lambdaschool.potluckbackend.exceptions;

public class ResourceNotFoundException
    extends RuntimeException
{
    public ResourceNotFoundException(String message)
    {
        super("Error From Potluck Application " + message);
    }
}
