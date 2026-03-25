package com.luv2code.spring_boot_library.service;

import com.luv2code.spring_boot_library.dao.BookRepository;
import com.luv2code.spring_boot_library.dao.ReviewRepository;
import com.luv2code.spring_boot_library.entity.Review;
import com.luv2code.spring_boot_library.requestmodels.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;

@Service
@Transactional
public class ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void postReview(String userEmail, ReviewRequest reviewRequest) throws Exception {
        Review validateReview = reviewRepository.findByUserEmailAndBookId(userEmail, reviewRequest.getBookId());
        if (validateReview != null) {
            //user already created a review for this book
            throw new Exception("Review already created");
        }

        Review review = new Review();
        review.setBookId(reviewRequest.getBookId());
        review.setRating(reviewRequest.getRating());
        review.setUserEmail(userEmail);

        if (reviewRequest.getReviewDescription().isPresent()) {
            //Need to use map because Optional copies strings a bit weird
            review.setReviewDescription(reviewRequest.getReviewDescription().map(
                    Object::toString
            ).orElse(null));
        }
//        if (reviewRequest.getReviewDescription() != null &&
//                !reviewRequest.getReviewDescription().isEmpty()) {
//
//            review.setReviewDescription(reviewRequest.getReviewDescription());
//            System.out.println("Description: " + reviewRequest.getReviewDescription());
//        }

        review.setDate(Date.valueOf(LocalDate.now()));
        reviewRepository.save(review);
    }

    //if user already posted a review for book with bookId
    public Boolean userReviewListed(String userEmail, Long bookId) {
        Review validateReview = reviewRepository.findByUserEmailAndBookId(userEmail, bookId);

        return validateReview != null;
    }
}
