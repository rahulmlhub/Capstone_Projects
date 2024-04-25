package com.metlife.review.payload;

/**
 * @author Admin
 */

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {
    private String reviewId;
    private String bookingId;
    private int rating;
    private String comment;
}

