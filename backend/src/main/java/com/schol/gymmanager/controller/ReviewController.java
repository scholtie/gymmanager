package com.schol.gymmanager.controller;

import com.schol.gymmanager.model.DTOs.GymDto;
import com.schol.gymmanager.model.DTOs.ReviewDto;
import com.schol.gymmanager.model.Gym;
import com.schol.gymmanager.model.Review;
import com.schol.gymmanager.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/review")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping("/gym/{id}")
    public List<Review> findAllByGymId(@PathVariable long id) {
        return reviewService.findAllReviewsByGymId(id);
    }

    @GetMapping("/customer/{id}")
    public List<Review> findAllByCustomerId(@PathVariable long id) {
        return reviewService.findAllReviewsByCustomerId(id);
    }

    @GetMapping("/trainer/{id}")
    public List<Review> findAllByTrainerId(@PathVariable long id) {
        return reviewService.findAllReviewsByTrainerId(id);
    }

    @PostMapping("/")
    public Review create(@RequestBody ReviewDto reviewDto) {
        return reviewService.create(reviewDto);
    }
    @GetMapping("/gym/{id}/average")
    public Long getAverageRatingForGym(@PathVariable Long id){
        return reviewService.getAverageReviewByGymId(id);
    }

    @GetMapping("/trainer/{id}/average")
    public long getAverageRatingForTrainer(@PathVariable long id){
        return reviewService.getAverageReviewByTrainerId(id);
    }
}
