package com.schol.gymmanager.service;

import com.schol.gymmanager.exception.InsufficientRoleException;
import com.schol.gymmanager.model.DTOs.ReviewDto;
import com.schol.gymmanager.model.Review;
import com.schol.gymmanager.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final GymService gymService;
    private final CustomerService customerService;
    private final TrainerService trainerService;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository,
                         GymService gymService,
                         CustomerService customerService,
                         TrainerService trainerService) {
        this.reviewRepository = reviewRepository;
        this.gymService = gymService;
        this.customerService = customerService;
        this.trainerService = trainerService;
    }

    public List<Review> findAllReviewsByGymId(long gymId) {
        return reviewRepository.findAllByGymId(gymId);
    }

    public List<Review> findAllReviewsByTrainerId(long trainerId) {
        return reviewRepository.findAllByTrainerId(trainerId);
    }

    public List<Review> findAllReviewsByCustomerId(long customerId) {
        return reviewRepository.findAllByCustomerId(customerId);
    }

    public Long getAverageReviewByGymId(Long gymId) {
        return reviewRepository.getAverageRatingGym(gymId);
    }

    public Long getAverageReviewByTrainerId(Long trainerId) {
        return reviewRepository.getAverageRatingTrainer(trainerId);
    }

    public Review create(ReviewDto reviewDto) {
        Review review = new Review();

        if (reviewDto.getGymId() != null) {
            review.setGym(gymService.findById(reviewDto.getGymId()));
        }

        if (reviewDto.getTrainerId() != null) {
            review.setTrainer(trainerService.findById(reviewDto.getTrainerId()));
        }

        customerService.getLoggedInCustomer().ifPresentOrElse(
                review::setCustomer,
                () -> {
                    throw new InsufficientRoleException();
                });

        review.setComment(reviewDto.getComment());
        review.setRating(reviewDto.getRating());

        return reviewRepository.save(review);
    }

    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }
}
