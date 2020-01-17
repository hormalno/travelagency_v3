package com.travelagency_v2.travelagency_v2.serviceImpl;


import com.travelagency_v2.travelagency_v2.repositories.BookingProductRepository;
import com.travelagency_v2.travelagency_v2.repositories.ReviewRepository;
import com.travelagency_v2.travelagency_v2.repositories.UserRepository;
import com.travelagency_v2.travelagency_v2.services.ReviewService;
import com.travelagency_v2.travelagency_v2.entities.BookingProduct;
import com.travelagency_v2.travelagency_v2.entities.Review;
import com.travelagency_v2.travelagency_v2.entities.User;
import com.travelagency_v2.travelagency_v2.models.bindingModels.ReviewDto;
import com.travelagency_v2.travelagency_v2.models.viewModels.ReviewByUserViewModel;
import com.travelagency_v2.travelagency_v2.models.viewModels.ReviewViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ModelMapper modelMapper;

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    private final BookingProductRepository bookingProductRepository;

    @Autowired
    public ReviewServiceImpl(ModelMapper modelMapper, ReviewRepository reviewRepository,
                             UserRepository userRepository, BookingProductRepository bookingProductRepository) {
        this.modelMapper = modelMapper;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.bookingProductRepository = bookingProductRepository;
    }

    @Override
    public ReviewViewModel create(ReviewDto reviewDto) {
        Review review = new Review();
        review.setContent(reviewDto.getContent());
        review.setScore(reviewDto.getScore());
        User user = this.userRepository.findOneByUsername(reviewDto.getUsername());
        review.setUser(user);
        BookingProduct bookingProduct = this.bookingProductRepository.findOneById(reviewDto.getBookingProductId());
        review.setBookingProduct(bookingProduct);
        review.setReleaseDate(new Date());
        this.reviewRepository.saveAndFlush(review);
        return this.modelMapper.map(review,ReviewViewModel.class);
    }

    @Override
    public List<ReviewViewModel> findAllByProductId(long id) {
        List<Review> reviews = this.reviewRepository.findAllByBookingProductId(id);
        List<ReviewViewModel> reviewViewModels = new ArrayList<>();
        for (Review review : reviews) {
            ReviewViewModel reviewViewModel = this.modelMapper.map(review,ReviewViewModel.class);
            reviewViewModels.add(reviewViewModel);
        }
        return reviewViewModels;
    }

    @Override
    public List<ReviewByUserViewModel> findAllByUsername(String username) {
        List<ReviewByUserViewModel> reviewByUserViewModels = new ArrayList<>();
        List<Review> reviews = this.reviewRepository.findAllByUserUsername(username);
        for (Review review : reviews) {
            ReviewByUserViewModel reviewByUserViewModel = this.modelMapper.map(review,ReviewByUserViewModel.class);
            reviewByUserViewModels.add(reviewByUserViewModel);
        }
        return reviewByUserViewModels;
    }
}
