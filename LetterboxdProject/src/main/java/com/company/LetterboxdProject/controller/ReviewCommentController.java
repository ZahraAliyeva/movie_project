package com.company.LetterboxdProject.controller;

import com.company.LetterboxdProject.dto.CommentRequest;
import com.company.LetterboxdProject.dto.CommentResponse;
import com.company.LetterboxdProject.service.inter.ReviewCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class ReviewCommentController {

    private final ReviewCommentService commentService;

    @PostMapping("/user/{userId}/review/{reviewId}")
    public ResponseEntity<?> saveComment(@PathVariable(name = "userId") Long userId,
                                        @PathVariable(name = "reviewId") Long reviewId,
                                        @RequestBody CommentRequest request) {
        final CommentResponse response = commentService.saveComment(userId, reviewId, request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/edit/{comment}")
    public ResponseEntity<?> editComment(@PathVariable(name = "comment") Long id,
                                        @RequestBody CommentRequest request) {
        final CommentResponse response = commentService.editComment(id, request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all/{review}")
    public ResponseEntity<?> getComments(@PathVariable("review") Long reviewId) {
        Set<CommentResponse> responses = commentService.getReviewComments(reviewId);

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getComment(@PathVariable("id") Long id) {
        CommentResponse responses = commentService.getReviewComment(id);

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @DeleteMapping("/{comment}")
    public ResponseEntity<?> deleteComment(@PathVariable(name = "comment") Long commentId) {
        String response = commentService.deleteComment(commentId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
