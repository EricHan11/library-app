package com.luv2code.spring_boot_library.controller;

import com.luv2code.spring_boot_library.requestmodels.ReviewRequest;
import com.luv2code.spring_boot_library.service.ReviewService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin allows us to connect with our React location
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController (ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    //returns if user has a review for such book already
    //used for front end to determine if user can write a review for a book
    @GetMapping("/secure/user/book")
    public Boolean reviewBookByUser(@AuthenticationPrincipal Jwt jwt,
                                    @RequestParam Long bookId) throws Exception {
        String userEmail = jwt.getClaim("email");

        if (userEmail == null) {
            throw new Exception("User email is missing");
        }
        return reviewService.userReviewListed(userEmail, bookId);
    }

    @PostMapping("/secure")
    public void postReview(@AuthenticationPrincipal Jwt jwt,
                           @RequestBody ReviewRequest reviewRequest) throws Exception {
        String userEmail = jwt.getClaim("email");
        if (userEmail == null) {
            throw new Exception("User email is missing");
        }
        reviewService.postReview(userEmail, reviewRequest);
    }
}
