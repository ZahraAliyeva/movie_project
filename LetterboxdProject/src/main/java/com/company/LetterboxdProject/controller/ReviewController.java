package com.company.LetterboxdProject.controller;

import com.company.LetterboxdProject.dto.ReviewRequest;
import com.company.LetterboxdProject.dto.ReviewResponse;
import com.company.LetterboxdProject.service.inter.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/user/{userId}/film/{filmId}")
    public ResponseEntity<?> saveReview(@PathVariable(name = "userId") Long userId,
            @PathVariable(name = "filmId") Long filmId,
            @RequestBody ReviewRequest reviewRequest) {
        final ReviewResponse response = reviewService.saveReview(userId, filmId, reviewRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/edit/{review}")
    public ResponseEntity<?> editReview(@PathVariable(name = "review") Long id,
            @RequestBody ReviewRequest reviewRequest) {
        final ReviewResponse response = reviewService.editReview(id, reviewRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/like/user/{user}/review/{reviewId}")
    private ResponseEntity<?> addReviewToLikedList(@PathVariable("user") Long userId,
                                                 @PathVariable("reviewId") Long reviewId) {
        reviewService.likeReview(userId, reviewId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/dislike/user/{user}/review/{reviewId}")
    private ResponseEntity<?> deleteReviewFromLikedList(@PathVariable("user") Long userId,
                                                      @PathVariable("reviewId") Long reviewId) {
        reviewService.dislikeReview(userId, reviewId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all/{film}")
    public ResponseEntity<?> getReviews(@PathVariable("film") Long filmId) {
        Set<ReviewResponse> responses = reviewService.getFilmReviews(filmId);

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

//    @GetMapping("/popular/this-week")
//    public ResponseEntity<?> getPopularReview() {
//        Set<ReviewResponse> responses = ;
//
//        return new ResponseEntity<>(responses, HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReview(@PathVariable("id") Long id) {
        ReviewResponse responses = reviewService.getFilmReview(id);

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/user/{friendId}/film/{film}")
    public ResponseEntity<?> getFriendReview(@PathVariable("friendId") Long friendId,
                                             @PathVariable("film") Long filmId) {
        Set<ReviewResponse> responses = reviewService.getFriendReview(friendId, filmId);

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @DeleteMapping("/{review}")
    public ResponseEntity<?> deleteReview(@PathVariable(name = "review") Long reviewId) {
        String response = reviewService.deleteReview(reviewId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
