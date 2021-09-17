package com.techelevator.model;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class Review {

    private Long id;
    @NotNull(message = "Beer ID field must not be blank")
    @Min(value = 1, message = "Invalid Beer ID")
    private Long beerId;
    @NotNull(message = "Reviewer ID field must not be blank")
    @Min(value = 1, message = "Invalid User ID")
    private Long reviewerId;
    @NotBlank(message = "Review body must not be blank")
    private String reviewText;
    @NotNull(message = "Rating field cannot be blank")
    @DecimalMin(value = "0.0", message = "Rating must be 0 or greater")
    @DecimalMax(value = "5.0", message = "Rating must be less than 5.0")
    private BigDecimal starRating;
    private boolean active;

    public Review() {
    }

    public Review(Long id, Long beerId, Long reviewerId, String reviewText, BigDecimal starRating) {
        this.id = id;
        this.beerId = beerId;
        this.reviewerId = reviewerId;
        this.reviewText = reviewText;
        this.starRating = starRating;
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBeerId() {
        return beerId;
    }

    public void setBeerId(Long beerId) {
        this.beerId = beerId;
    }

    public Long getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Long reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public BigDecimal getStarRating() {
        return starRating;
    }

    public void setStarRating(BigDecimal starRating) {
        this.starRating = starRating;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", beerId=" + beerId +
                ", reviewerId=" + reviewerId +
                ", reviewText='" + reviewText + '\'' +
                ", starRating=" + starRating +
                ", active=" + active +
                '}';
    }
}
