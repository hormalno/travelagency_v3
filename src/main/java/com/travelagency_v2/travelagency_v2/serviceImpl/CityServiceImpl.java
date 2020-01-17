package com.travelagency_v2.travelagency_v2.serviceImpl;

import com.travelagency_v2.travelagency_v2.repositories.CityRepository;
import com.travelagency_v2.travelagency_v2.entities.City;
import com.travelagency_v2.travelagency_v2.models.viewModels.CityViewModel;
import com.travelagency_v2.travelagency_v2.services.CityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<CityViewModel> findAll() {
        List<City> cities = this.cityRepository.findAll();
        List<CityViewModel> cityViewModels = new ArrayList<>();
        for (City city : cities) {
            CityViewModel cityViewModel = this.modelMapper.map(city,CityViewModel.class);
            cityViewModels.add(cityViewModel);
        }
        return cityViewModels;
    }


}
