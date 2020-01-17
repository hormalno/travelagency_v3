package com.travelagency_v2.travelagency_v2.serviceImpl;


import com.travelagency_v2.travelagency_v2.repositories.BookingProductRepository;
import com.travelagency_v2.travelagency_v2.repositories.CityRepository;
import com.travelagency_v2.travelagency_v2.repositories.ReviewRepository;
import com.travelagency_v2.travelagency_v2.entities.City;
import com.travelagency_v2.travelagency_v2.entities.Cruise;
import com.travelagency_v2.travelagency_v2.entities.Review;
import com.travelagency_v2.travelagency_v2.models.bindingModels.CruiseDto;
import com.travelagency_v2.travelagency_v2.models.viewModels.CruiseDetailViewModel;
import com.travelagency_v2.travelagency_v2.models.viewModels.CruiseViewModel;
import com.travelagency_v2.travelagency_v2.services.CruiseService;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CruiseServiceImpl implements CruiseService {

    private final BookingProductRepository bookingProductRepository;

    private final ModelMapper modelMapper;

    private final ReviewRepository reviewRepository;

    private final CityRepository cityRepository;

    @Autowired
    public CruiseServiceImpl(BookingProductRepository bookingProductRepository, ModelMapper modelMapper,
                             ReviewRepository reviewRepository, CityRepository cityRepository) {
        this.bookingProductRepository = bookingProductRepository;
        this.modelMapper = modelMapper;
        this.reviewRepository = reviewRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public void create(CruiseDto cruiseDto) {
        Cruise cruise = this.modelMapper.map(cruiseDto, Cruise.class);
        City origin = this.cityRepository.findByName(cruiseDto.getOriginName());
        cruise.setOrigin(origin);
        for (String destinationCityName : cruiseDto.getDestinationNames()) {
            City destination = this.cityRepository.findByName(destinationCityName);
            cruise.getDestinations().add(destination);
        }
        this.bookingProductRepository.saveAndFlush(cruise);
    }

    @Override
    public CruiseDetailViewModel findById(long id) {
        Cruise cruise = this.bookingProductRepository.findCruiseById(id);
        return this.modelMapper.map(cruise,CruiseDetailViewModel.class);
    }

    @Override
    public List<CruiseViewModel> findAll() {
        List<CruiseViewModel> cruiseViewModels = new ArrayList<>();
        List<Cruise> cruises = this.bookingProductRepository.findAllCruises();
        for (Cruise cruise : cruises) {
            CruiseViewModel cruiseViewModel = this.modelMapper.map(cruise,CruiseViewModel.class);
            cruiseViewModels.add(cruiseViewModel);
        }
        return cruiseViewModels;
    }

    @Override
    public void deleteById(long id) {
        List<Review> reviews = this.reviewRepository.findAllByBookingProductId(id);
        this.reviewRepository.deleteInBatch(reviews);
        this.bookingProductRepository.deleteById(id);
    }
}
