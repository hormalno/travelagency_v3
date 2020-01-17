package com.travelagency_v2.travelagency_v2.annotations;

import com.travelagency_v2.travelagency_v2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class UniqueValidator implements ConstraintValidator<Unique,Object> {

    private final UserService userService;

    @Autowired
    public UniqueValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(Unique unique) {

    }

    @Override
    public boolean isValid(Object username, ConstraintValidatorContext constraintValidatorContext) {
        return this.userService.findUsername(username);
    }
}
