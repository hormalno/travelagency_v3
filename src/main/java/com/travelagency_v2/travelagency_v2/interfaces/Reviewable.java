package com.travelagency_v2.travelagency_v2.interfaces;


import com.travelagency_v2.travelagency_v2.models.viewModels.ReviewViewModel;

import java.util.Set;

public interface Reviewable {

    Set<ReviewViewModel> getReviews();
}
