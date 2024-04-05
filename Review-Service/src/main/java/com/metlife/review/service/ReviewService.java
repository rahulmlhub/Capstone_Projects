package com.metlife.review.service;

/**
 * @author Admin
 */
import com.metlife.review.payload.ReviewDTO;

import java.util.List;

public interface ReviewService {
    ReviewDTO createReview(ReviewDTO reviewDTO);
    ReviewDTO updateReview(String reviewId, ReviewDTO reviewDTO);
    ReviewDTO getReviewById(String reviewId);
    List<ReviewDTO> getAllReviews();
    void deleteReviewById(String reviewId);
}

