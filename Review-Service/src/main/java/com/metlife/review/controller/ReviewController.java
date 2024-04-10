package com.metlife.review.controller;

import com.metlife.review.payload.ReviewDTO;
import com.metlife.review.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Admin
 */
@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {

    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewDTO reviewDTO) {
        logger.info("Creating a new review");
        ReviewDTO createdReview = reviewService.createReview(reviewDTO);
        logger.info("Review created successfully with ID: {}", createdReview.getReviewId());
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewDTO> updateReview(@PathVariable String reviewId, @RequestBody ReviewDTO reviewDTO) {
        logger.info("Updating review with ID: {}", reviewId);
        ReviewDTO updatedReview = reviewService.updateReview(reviewId, reviewDTO);
        logger.info("Review updated successfully with ID: {}", reviewId);
        return ResponseEntity.ok(updatedReview);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable String reviewId) {
        logger.info("Fetching review with ID: {}", reviewId);
        ReviewDTO reviewDTO = reviewService.getReviewById(reviewId);
        logger.info("Review fetched successfully with ID: {}", reviewId);
        return ResponseEntity.ok(reviewDTO);
    }

    @GetMapping
    public ResponseEntity<List<ReviewDTO>> getAllReviews() {
        logger.info("Fetching all reviews");
        List<ReviewDTO> reviewDTOs = reviewService.getAllReviews();
        logger.info("Fetched {} reviews", reviewDTOs.size());
        return ResponseEntity.ok(reviewDTOs);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReviewById(@PathVariable String reviewId) {
        logger.info("Deleting review with ID: {}", reviewId);
        reviewService.deleteReviewById(reviewId);
        logger.info("Review deleted successfully with ID: {}", reviewId);
        return ResponseEntity.noContent().build();
    }
}
