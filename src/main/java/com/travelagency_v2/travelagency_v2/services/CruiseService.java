package com.travelagency_v2.travelagency_v2.services;


import com.travelagency_v2.travelagency_v2.models.bindingModels.CruiseDto;
import com.travelagency_v2.travelagency_v2.models.viewModels.CruiseDetailViewModel;
import com.travelagency_v2.travelagency_v2.models.viewModels.CruiseViewModel;

import java.util.List;

public interface CruiseService {

    void create(CruiseDto cruiseDto);

    CruiseDetailViewModel findById(long id);

    List<CruiseViewModel> findAll();

    void deleteById(long id);
}
