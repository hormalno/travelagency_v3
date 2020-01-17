package com.travelagency_v2.travelagency_v2.utilities;


import com.travelagency_v2.travelagency_v2.interfaces.Reviewable;
import com.travelagency_v2.travelagency_v2.models.viewModels.ReviewViewModel;

public class ReviewScore {

    public static <S extends Reviewable> int set(S viewModel){
        if (viewModel.getReviews().size() == 0){
            return 0;
        }
        double tempScore = 0;
        for (ReviewViewModel review : viewModel.getReviews()) {
            tempScore += review.getScore();
        }
        return  (int) (tempScore/viewModel.getReviews().size());
    }
}
