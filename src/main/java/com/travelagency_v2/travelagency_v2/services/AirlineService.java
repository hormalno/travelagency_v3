package com.travelagency_v2.travelagency_v2.services;


import com.travelagency_v2.travelagency_v2.models.viewModels.AirlineViewModel;

import java.util.List;

public interface AirlineService {

    List<AirlineViewModel> findAll();
}
