package com.travelagency_v2.travelagency_v2.services;


import com.travelagency_v2.travelagency_v2.models.bindingModels.ReviewDto;
import com.travelagency_v2.travelagency_v2.models.viewModels.ReviewByUserViewModel;
import com.travelagency_v2.travelagency_v2.models.viewModels.ReviewViewModel;

import java.util.List;

public interface ReviewService {

    ReviewViewModel create(ReviewDto reviewDto);

    List<ReviewViewModel> findAllByProductId(long id);

    List<ReviewByUserViewModel> findAllByUsername(String name);
}
