package com.travelagency_v2.travelagency_v2.serviceImpl;

import com.travelagency_v2.travelagency_v2.repositories.AirlineRepository;
import com.travelagency_v2.travelagency_v2.repositories.BookingProductRepository;
import com.travelagency_v2.travelagency_v2.repositories.CityRepository;
import com.travelagency_v2.travelagency_v2.repositories.ReviewRepository;
import com.travelagency_v2.travelagency_v2.services.FlightService;
import com.travelagency_v2.travelagency_v2.entities.Airline;
import com.travelagency_v2.travelagency_v2.entities.City;
import com.travelagency_v2.travelagency_v2.entities.Flight;
import com.travelagency_v2.travelagency_v2.entities.Review;
import com.travelagency_v2.travelagency_v2.models.bindingModels.FlightDto;
import com.travelagency_v2.travelagency_v2.models.viewModels.FlightDetailViewModel;
import com.travelagency_v2.travelagency_v2.models.viewModels.FlightViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {

    private final BookingProductRepository bookingProductRepository;

    private final CityRepository cityRepository;

    private final AirlineRepository airlineRepository;

    private final ReviewRepository reviewRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public FlightServiceImpl(BookingProductRepository bookingProductRepository, CityRepository cityRepository,
                             AirlineRepository airlineRepository, ReviewRepository reviewRepository, ModelMapper modelMapper) {
        this.bookingProductRepository = bookingProductRepository;
        this.cityRepository = cityRepository;
        this.airlineRepository = airlineRepository;
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(FlightDto flightDto) {
        Flight flight = this.modelMapper.map(flightDto, Flight.class);
        City originCity = this.cityRepository.findByName(flightDto.getOriginName());
        City destinationCity = this.cityRepository.findByName(flightDto.getDestinationName());
        Airline airline = this.airlineRepository.findByName(flightDto.getAirlineName());
        flight.setOrigin(originCity);
        flight.setDestination(destinationCity);
        flight.setAirline(airline);
        this.bookingProductRepository.saveAndFlush(flight);
    }

    @Override
    public List<FlightViewModel> findAll() {
        List<FlightViewModel> flightViewModels = new ArrayList<>();
        List<Flight> flights = this.bookingProductRepository.findAllFlights();
        for (Flight flight : flights) {
            FlightViewModel flightViewModel = this.modelMapper.map(flight,FlightViewModel.class);
            flightViewModels.add(flightViewModel);
        }
        return flightViewModels;
    }

    @Override
    public FlightDetailViewModel findById(long id) {
        Flight flight = this.bookingProductRepository.findFlightById(id);
        return this.modelMapper.map(flight,FlightDetailViewModel.class);
    }

    @Override
    public void deleteById(long id) {
        List<Review> reviews = this.reviewRepository.findAllByBookingProductId(id);
        this.reviewRepository.deleteInBatch(reviews);
        this.bookingProductRepository.deleteById(id);
    }
}
