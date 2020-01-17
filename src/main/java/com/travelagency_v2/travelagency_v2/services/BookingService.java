package com.travelagency_v2.travelagency_v2.services;


import com.travelagency_v2.travelagency_v2.models.bindingModels.BookingDto;
import com.travelagency_v2.travelagency_v2.models.viewModels.BookingViewModel;

import java.util.List;

public interface BookingService {

    BookingViewModel create(BookingDto bookingDto);

    List<BookingViewModel> findAllByUsername(String name);
}
