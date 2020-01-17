package com.travelagency_v2.travelagency_v2.services;

import com.travelagency_v2.travelagency_v2.models.bindingModels.FlightDto;
import com.travelagency_v2.travelagency_v2.models.viewModels.FlightDetailViewModel;
import com.travelagency_v2.travelagency_v2.models.viewModels.FlightViewModel;

import java.util.List;


public interface FlightService {

    void create(FlightDto flightDto);

    List<FlightViewModel> findAll();

    FlightDetailViewModel findById(long id);

    void deleteById(long id);
}
