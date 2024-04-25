package com.metlife.review.service.imp;

import com.metlife.review.entity.Review;
import com.metlife.review.exception.ResourceNotFoundException;
import com.metlife.review.payload.ReviewDTO;
import com.metlife.review.repository.ReviewRepository;
import com.metlife.review.service.ReviewService;
import com.metlife.review.util.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private IdGenerator idGenerator;
    private static final Logger logger = LoggerFactory.getLogger(ReviewServiceImpl.class);

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        logger.info("Creating a new review");
        Review review = new Review();
        BeanUtils.copyProperties(reviewDTO, review);
        review.setReviewId(idGenerator.generateId());
        Review savedReview = reviewRepository.save(review);
        ReviewDTO savedReviewDTO = new ReviewDTO();
        BeanUtils.copyProperties(savedReview, savedReviewDTO);
        logger.info("Review created successfully with ID: {}", savedReviewDTO.getReviewId());
        return savedReviewDTO;
    }

    @Override
    public ReviewDTO updateReview(String reviewId, ReviewDTO reviewDTO) {
        logger.info("Updating review with ID: {}", reviewId);
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with ID: " + reviewId));
        BeanUtils.copyProperties(reviewDTO, review);
        Review updatedReview = reviewRepository.save(review);
        ReviewDTO updatedReviewDTO = new ReviewDTO();
        BeanUtils.copyProperties(updatedReview, updatedReviewDTO);
        logger.info("Review updated successfully with ID: {}", reviewId);
        return updatedReviewDTO;
    }

    @Override
    public ReviewDTO getReviewById(String reviewId) {
        logger.info("Fetching review with ID: {}", reviewId);
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with ID: " + reviewId));
        ReviewDTO reviewDTO = new ReviewDTO();
        BeanUtils.copyProperties(review, reviewDTO);
        logger.info("Review fetched successfully with ID: {}", reviewId);
        return reviewDTO;
    }

    @Override
    public List<ReviewDTO> getAllReviews() {
        logger.info("Fetching all reviews");
        List<Review> reviews = reviewRepository.findAll();
        List<ReviewDTO> reviewDTOs = reviews.stream()
                .map(review -> {
                    ReviewDTO reviewDTO = new ReviewDTO();
                    BeanUtils.copyProperties(review, reviewDTO);
                    return reviewDTO;
                })
                .collect(Collectors.toList());
        logger.info("Fetched {} reviews", reviewDTOs.size());
        return reviewDTOs;
    }

    @Override
    public void deleteReviewById(String reviewId) {
        logger.info("Deleting review with ID: {}", reviewId);
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with ID: " + reviewId));
        reviewRepository.delete(review);
        logger.info("Review deleted successfully with ID: {}", reviewId);
    }
}
