package com.travelagency_v2.travelagency_v2.serviceImpl;



import com.travelagency_v2.travelagency_v2.repositories.AirlineRepository;
import com.travelagency_v2.travelagency_v2.entities.Airline;
import com.travelagency_v2.travelagency_v2.models.viewModels.AirlineViewModel;
import com.travelagency_v2.travelagency_v2.services.AirlineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public AirlineServiceImpl(AirlineRepository airlineRepository,ModelMapper modelMapper) {
        this.airlineRepository = airlineRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AirlineViewModel> findAll() {
        List<AirlineViewModel> airlineViewModels = new ArrayList<>();
        List<Airline> airlines = this.airlineRepository.findAll();
        for (Airline airline : airlines) {
            AirlineViewModel airlineViewModel = this.modelMapper.map(airline,AirlineViewModel.class);
            airlineViewModels.add(airlineViewModel);
        }
        return airlineViewModels;
    }
}
