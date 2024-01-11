package com.company.LetterboxdProject.service.inter;

import com.company.LetterboxdProject.dto.ReviewRequest;
import com.company.LetterboxdProject.dto.ReviewResponse;

import java.util.Set;

public interface ReviewService {

    ReviewResponse saveReview(final Long userId, final Long filmId, final ReviewRequest reviewRequest);
    ReviewResponse editReview(final Long reviewId, final ReviewRequest reviewRequest);
    Set<ReviewResponse> getFilmReviews(final Long filmId);
    Set<ReviewResponse> getPopularReviews();
    ReviewResponse getFilmReview(final Long reviewId);
    Set<ReviewResponse> getFriendReview(final Long friendId, final Long filmId);
    void likeReview(final Long userId, final Long reviewId);
    void dislikeReview(final Long userId, final Long reviewId);
    String deleteReview(final Long reviewId);
}
