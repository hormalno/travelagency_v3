package com.travelagency_v2.travelagency_v2.serviceImpl;


import com.travelagency_v2.travelagency_v2.config.Error;
import com.travelagency_v2.travelagency_v2.repositories.CityRepository;
import com.travelagency_v2.travelagency_v2.repositories.UserRepository;
import com.travelagency_v2.travelagency_v2.entities.City;
import com.travelagency_v2.travelagency_v2.entities.User;
import com.travelagency_v2.travelagency_v2.models.bindingModels.UserDto;
import com.travelagency_v2.travelagency_v2.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final CityRepository cityRepository;

    private final ModelMapper modelMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CityRepository cityRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void create(UserDto userDto) {
        User user = this.modelMapper.map(userDto,User.class);
        user.setPassword(this.bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        City city = this.cityRepository.findByName(userDto.getCityName());
        user.setCity(city);
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void delete() {

    }

    @Override
    public boolean findUsername(Object username) {
        User user = this.userRepository.findOneByUsername((String) username);
        if (user == null){
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findOneByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(Error.INVALID_CREDENTIALS);
        }

        return user;
    }
}
