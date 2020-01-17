package com.travelagency_v2.travelagency_v2.controllers;

import com.travelagency_v2.travelagency_v2.models.bindingModels.ReviewDto;
import com.travelagency_v2.travelagency_v2.models.viewModels.ReviewViewModel;
import com.travelagency_v2.travelagency_v2.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;

@Controller
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews/{productId}")
    public ResponseEntity<List<ReviewViewModel>> getReviewsById(@PathVariable long productId){
        List<ReviewViewModel> reviewViewModels = this.reviewService.findAllByProductId(productId);
        return new ResponseEntity<List<ReviewViewModel>>(reviewViewModels, HttpStatus.OK);
    }

    @PostMapping("/reviews/{productId}")
    public ResponseEntity saveItem(@PathVariable long productId, @RequestBody ReviewDto reviewDto,
                                   @AuthenticationPrincipal Principal principal){
        reviewDto.setBookingProductId(productId);
        reviewDto.setUsername(principal.getName());
        ReviewViewModel reviewViewModel = this.reviewService.create(reviewDto);
        return new ResponseEntity<ReviewViewModel>(reviewViewModel,HttpStatus.OK);
    }


}
