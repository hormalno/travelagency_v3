package com.travelagency_v2.travelagency_v2.services;

import com.travelagency_v2.travelagency_v2.models.bindingModels.HotelDto;
import com.travelagency_v2.travelagency_v2.models.viewModels.HotelDetailViewModel;
import com.travelagency_v2.travelagency_v2.models.viewModels.HotelViewModel;

import java.util.List;

public interface HotelService {

    void create(HotelDto hotelDto);

    List<HotelViewModel> findAll();

    HotelDetailViewModel findById(long id);

    void deleteById(long id);
}
