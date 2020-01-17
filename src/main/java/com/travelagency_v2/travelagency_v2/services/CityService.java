package com.travelagency_v2.travelagency_v2.services;


import com.travelagency_v2.travelagency_v2.models.viewModels.CityViewModel;
import java.util.List;

public interface CityService {

    List<CityViewModel> findAll();
}
