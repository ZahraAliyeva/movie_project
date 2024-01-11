package com.company.LetterboxdProject.service.impl;

import com.company.LetterboxdProject.dto.ReviewRequest;
import com.company.LetterboxdProject.dto.ReviewResponse;
import com.company.LetterboxdProject.entity.Review;
import com.company.LetterboxdProject.entity.ReviewLikeHistory;
import com.company.LetterboxdProject.entity.Tags;
import com.company.LetterboxdProject.repository.*;
import com.company.LetterboxdProject.service.inter.ReviewService;
import com.company.LetterboxdProject.service.inter.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.company.LetterboxdProject.util.Mapper.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repository;
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;
    private final TagService tagService;
    private final FilmRateHistoryRepository filmRateHistoryRepository;
    private final ReviewLikeHistoryRepository reviewLikeHistoryRepository;

    @Override
    public ReviewResponse saveReview(Long userId, Long filmId, ReviewRequest reviewRequest) {
        final Review review = toModel(reviewRequest);

        final Set<Tags> tags = tagService.saveTags(reviewRequest.getTags());

        review.setUser(userRepository.findById(userId).get());
        review.setFilm(filmRepository.findById(filmId).get());
        review.setReviewDate(LocalDate.now());
        review.setTags(tags);

        return reviewToResponse(repository.save(review));
    }

    @Override
    public ReviewResponse editReview(Long reviewId, ReviewRequest reviewRequest) {
        final Review review = this.validateIfPresent(reviewId);

        map(reviewRequest, review);

        review.setUpdatedDate(LocalDate.now());
        return reviewToResponse(repository.save(review));
    }

    @Override
    public Set<ReviewResponse> getFilmReviews(Long filmId) {
        Set<Review> reviews = repository.findByFilmId(filmId);

        Set<ReviewResponse> responses = reviews.stream()
                .filter(r -> r != null)
                .map(review ->
                        reviewToResponse(review))
                .collect(Collectors.toSet());

        return responses;
    }

    @Override
    public Set<ReviewResponse> getPopularReviews() {
//        Set<Review> reviews = repository.findByFilmId(filmId);
//
//        Set<ReviewResponse> responses = reviews.stream()
//                .filter(r -> r != null)
//                .map(review ->
//                        reviewToResponse(review))
//                .collect(Collectors.toSet());
//
//        return responses;
        return null;
    }

    @Override
    public ReviewResponse getFilmReview(Long reviewId) {
        final Review review = this.validateIfPresent(reviewId);
        return reviewToResponse(review);
    }

    @Override
    public Set<ReviewResponse> getFriendReview(Long friendId, Long filmId) {
        final Set<Review> reviews = repository.findByFriends(friendId, filmId);

        return reviews.stream()
                .filter(r -> r != null)
                .map(review ->
                        reviewToResponse(review))
                .collect(Collectors.toSet());
    }

    @Override
    public void likeReview(Long userId, Long reviewId) {
        final ReviewLikeHistory reviewLikeHistory = this.findReviewLikeHistory(userId, reviewId);
        reviewLikeHistory.setLikes(true);
        reviewLikeHistory.setLikeDate(LocalDate.now());

        reviewLikeHistoryRepository.save(reviewLikeHistory);

    }
    @Override
    public void dislikeReview(Long userId, Long reviewId) {
        final ReviewLikeHistory reviewLikeHistory = this.findReviewLikeHistory(userId, reviewId);

        reviewLikeHistory.setLikes(false);
        reviewLikeHistory.setLikeDate(LocalDate.now());

        reviewLikeHistoryRepository.save(reviewLikeHistory);
    }

    @Override
    public String deleteReview(Long reviewId) {
        try {
            final Review review = this.validateIfPresent(reviewId);
            repository.delete(review);

            return review.getUser().getUserName() + "deleted this review";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ReviewResponse reviewToResponse(Review review) {
        ReviewResponse response = toResponse(repository.save(review));
        if(filmRateHistoryRepository.findFilmRate(review.getUser().getId(), review.getFilm().getId()) != null) {
            response.setRate(filmRateHistoryRepository.findFilmRate(review.getUser().getId(), review.getFilm().getId()));
        }
        if(filmRateHistoryRepository.findWatchedDate(review.getUser().getId(), review.getFilm().getId()) != null) {
            response.setWatchedDate(filmRateHistoryRepository.findWatchedDate(review.getUser().getId(), review.getFilm().getId()));
        }
        response.setNumberOfLikes(reviewLikeHistoryRepository.findByLikes(review.getId()));

        return response;
    }
    private Review validateIfPresent(final Long id) {
        final Optional<Review> review = repository.findById(id);
        return review.get();
    }
    private ReviewLikeHistory findReviewLikeHistory(Long userId, Long reviewId) {
        ReviewLikeHistory reviewLikeHistory = reviewLikeHistoryRepository.findByUserAndReview(userId, reviewId);
        if(reviewLikeHistory == null) {
            reviewLikeHistory = new ReviewLikeHistory();
            reviewLikeHistory.setReviews(this.validateIfPresent(reviewId));
            reviewLikeHistory.setUser(userRepository.findById(userId).get());

            reviewLikeHistoryRepository.save(reviewLikeHistory);
        }

        return reviewLikeHistory;
    }
}
