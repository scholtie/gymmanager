package com.schol.gymmanager.service;

import com.schol.gymmanager.exception.EntityNotFoundException;
import com.schol.gymmanager.model.*;
import com.schol.gymmanager.model.DTOs.GymDto;
import com.schol.gymmanager.model.DTOs.ReviewDto;
import com.schol.gymmanager.repository.CustomerRepository;
import com.schol.gymmanager.repository.GymRepository;
import com.schol.gymmanager.repository.ReviewRepository;
import com.schol.gymmanager.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private GymService gymService;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private TrainerService trainerService;


    public List<Review> findAllReviewsByGymId(long id) {
        return reviewRepository.findAllByGymId(id);
    }

    public List<Review> findAllReviewsByTrainerId(long id) {
        return reviewRepository.findAllByTrainerId(id);
    }

    public List<Review> findAllReviewsByCustomerId(long id) {
        return reviewRepository.findAllByCustomerId(id);
    }

    public Long getAverageReviewByGymId(Long id) {
        return reviewRepository.getAverageRatingGym(id);
    }

    public Long getAverageReviewByTrainerId(Long id) {
        return reviewRepository.getAverageRatingTrainer(id);
    }


    public Review create(ReviewDto reviewDto){
        Review review = new Review();
        if (reviewDto.getGymId() != null) {
            review.setGym(gymService.findById(reviewDto.getGymId()));
        }
        if (reviewDto.getTrainerId() != null) {
            review.setTrainer(trainerService.findById(reviewDto.getTrainerId()));
        }
        if (customerService.getLoggedInCustomer().isPresent()) {
            review.setCustomer(customerService.getLoggedInCustomer().get());
        }
        review.setComment(reviewDto.getComment());
        review.setRating(reviewDto.getRating());
        return reviewRepository.save(review);
    }

    public void delete(Long id){
        reviewRepository.deleteById(id);
    }
}
