package com.metlife.review.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * @author Admin
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {

    @Id
    private String reviewId;
    private String bookingId;
    private int rating;
    private String comment;

    // Getters and setters
}
