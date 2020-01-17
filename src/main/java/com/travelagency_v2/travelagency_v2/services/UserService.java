package com.travelagency_v2.travelagency_v2.services;


import com.travelagency_v2.travelagency_v2.models.bindingModels.UserDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void create(UserDto userDto);

    @PreAuthorize("hasRole('ADMIN')")
    void delete();

    boolean findUsername(Object username);
}
