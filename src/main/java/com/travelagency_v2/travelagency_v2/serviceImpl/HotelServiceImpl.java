package com.travelagency_v2.travelagency_v2.serviceImpl;


import com.travelagency_v2.travelagency_v2.repositories.BookingProductRepository;
import com.travelagency_v2.travelagency_v2.repositories.CityRepository;
import com.travelagency_v2.travelagency_v2.repositories.ReviewRepository;
import com.travelagency_v2.travelagency_v2.entities.City;
import com.travelagency_v2.travelagency_v2.entities.Hotel;
import com.travelagency_v2.travelagency_v2.entities.Review;
import com.travelagency_v2.travelagency_v2.models.bindingModels.HotelDto;
import com.travelagency_v2.travelagency_v2.models.viewModels.HotelDetailViewModel;
import com.travelagency_v2.travelagency_v2.models.viewModels.HotelViewModel;
import com.travelagency_v2.travelagency_v2.services.HotelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class HotelServiceImpl implements HotelService {

    private final BookingProductRepository bookingProductRepository;
    private final ReviewRepository reviewRepository;
    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public HotelServiceImpl(BookingProductRepository bookingProductRepository, ReviewRepository reviewRepository,
                            ModelMapper modelMapper, CityRepository cityRepository) {
        this.bookingProductRepository = bookingProductRepository;
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
        this.cityRepository = cityRepository;
    }

    @Override
    public void create(HotelDto hotelDto) {
        Hotel hotel = this.modelMapper.map(hotelDto,Hotel.class);
        City city = this.cityRepository.findByName(hotelDto.getCityName());
        hotel.setCity(city);
        this.bookingProductRepository.saveAndFlush(hotel);
    }

    @Override
    public List<HotelViewModel> findAll() {
       List<HotelViewModel> hotelViewModels = new ArrayList<>();
       List<Hotel> hotels = this.bookingProductRepository.findAllHotels();
        for (Hotel hotel : hotels) {
            HotelViewModel hotelViewModel = this.modelMapper.map(hotel,HotelViewModel.class);
            hotelViewModels.add(hotelViewModel);
        }
        return hotelViewModels;
    }

    @Override
    public HotelDetailViewModel findById(long id) {
        Hotel hotel = this.bookingProductRepository.findHotelById(id);
        return this.modelMapper.map(hotel,HotelDetailViewModel.class);
    }

    @Override
    public void deleteById(long id) {
        List<Review> reviews = this.reviewRepository.findAllByBookingProductId(id);
        this.reviewRepository.deleteInBatch(reviews);
        this.bookingProductRepository.deleteById(id);
    }


}
