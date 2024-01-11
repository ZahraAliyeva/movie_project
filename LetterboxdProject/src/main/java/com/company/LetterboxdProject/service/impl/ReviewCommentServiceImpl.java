package com.company.LetterboxdProject.service.impl;

import com.company.LetterboxdProject.dto.CommentRequest;
import com.company.LetterboxdProject.dto.CommentResponse;
import com.company.LetterboxdProject.entity.ReviewComment;
import com.company.LetterboxdProject.entity.Tags;
import com.company.LetterboxdProject.repository.ReviewCommentRepository;
import com.company.LetterboxdProject.repository.ReviewRepository;
import com.company.LetterboxdProject.repository.UserRepository;
import com.company.LetterboxdProject.service.inter.ReviewCommentService;
import com.company.LetterboxdProject.service.inter.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.company.LetterboxdProject.util.Mapper.*;

@Service
@RequiredArgsConstructor
public class ReviewCommentServiceImpl implements ReviewCommentService {

    private final ReviewCommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final TagService tagService;

    @Override
    public CommentResponse saveComment(Long userId, Long filmId, CommentRequest request) {
        final ReviewComment comment = toModel(request);

        final Set<Tags> tags = tagService.saveTags(request.getTags());

        comment.setUser(userRepository.findById(userId).get());
        comment.setReviews(reviewRepository.findById(filmId).get());
        comment.setCommentDate(LocalDate.now());
        comment.setTags(tags);

        CommentResponse  response = toResponse(commentRepository.save(comment));
        return response;
    }

    @Override
    public CommentResponse editComment(Long reviewId, CommentRequest request) {
        final ReviewComment comment = this.validateIfPresent(reviewId);

        map(request, comment);

        return toResponse(commentRepository.save(comment));
    }

    @Override
    public Set<CommentResponse> getReviewComments(Long reviewId) {
        Set<ReviewComment> comments = commentRepository.findByReviews(reviewId);

        Set<CommentResponse> responses = comments.stream()
                .filter(c -> c != null)
                .map(c -> toResponse(c))
                .collect(Collectors.toSet());

        return responses;
    }

    @Override
    public CommentResponse getReviewComment(Long commentId) {
        final ReviewComment comment = this.validateIfPresent(commentId);
        return toResponse(comment);
    }

    @Override
    public String deleteComment(Long commentId) {
        try{
           final ReviewComment comment = this.validateIfPresent(commentId);
           commentRepository.delete(comment);

            return comment.getUser().getUserName() + "deleted this comment";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ReviewComment validateIfPresent(final Long id) {
        final Optional<ReviewComment> comment = commentRepository.findById(id);
        return comment.get();
    }
}
