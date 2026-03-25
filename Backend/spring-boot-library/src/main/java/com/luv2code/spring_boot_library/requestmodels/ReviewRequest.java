package com.luv2code.spring_boot_library.requestmodels;

import lombok.Data;

import java.util.Optional;

//This is what the client will send us, the review a user created
@Data
public class ReviewRequest {
    private double rating;

    private Long bookId;

    //Review text not required when leaving a review for a book, so type optional
    private Optional<String> reviewDescription;
    //private String reviewDescription;

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Optional<String> getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(Optional<String> reviewDescription) {
        this.reviewDescription = reviewDescription;
    }
}
