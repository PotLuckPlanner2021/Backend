package com.lambdaschool.potluckbackend.services;

import com.lambdaschool.potluckbackend.models.ValidationError;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;

import java.util.List;

/**
 * Class contains helper functions - functions that are needed throughout the application. The class can be autowired
 * into any class.
 */
public interface HelperFunctions
{

    /**
     * Searches to see if the exception has any constraint violations to report
     */

    List<ValidationError> getConstraintViolation(Throwable cause);

    /**
     * Checks to see if the authenticated user has access to modify the requested user's information
     */
    boolean isAuthorizedToMakeChange(String username);
}
