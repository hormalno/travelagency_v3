package com.travelagency_v2.travelagency_v2.services;

import com.travelagency_v2.travelagency_v2.models.viewModels.BookingProductViewModel;

public interface BookingProductService {

    BookingProductViewModel findById(long id);
}
