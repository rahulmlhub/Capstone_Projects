package com.metlife.review.repository;

import com.metlife.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Admin
 */
public interface ReviewRepository extends JpaRepository<Review, String> {
}
