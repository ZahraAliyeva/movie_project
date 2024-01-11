package com.company.LetterboxdProject.service.inter;

import com.company.LetterboxdProject.dto.CommentRequest;
import com.company.LetterboxdProject.dto.CommentResponse;

import java.util.Set;

public interface ReviewCommentService {

    CommentResponse saveComment(final Long userId, final Long filmId, final CommentRequest CommentRequest);
    CommentResponse editComment(final Long reviewId, final CommentRequest CommentRequest);
    Set<CommentResponse> getReviewComments(final Long reviewId);
    CommentResponse getReviewComment(final Long commentId);
    String deleteComment(final Long commentId);
}
