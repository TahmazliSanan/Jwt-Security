package org.pronet.app.controllers;

import lombok.RequiredArgsConstructor;
import org.pronet.app.requests.comment.CommentCreateRequest;
import org.pronet.app.requests.comment.CommentUpdateRequest;
import org.pronet.app.responses.CommentResponse;
import org.pronet.app.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping(value = "/create")
    public ResponseEntity<CommentResponse> createComment(@RequestBody CommentCreateRequest request) {
        CommentResponse createdComment = commentService.createComment(request);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<CommentResponse>> getCommentList(
            @RequestParam Optional<Long> userId,
            @RequestParam Optional<Long> postId) {
        List<CommentResponse> commentList = commentService.getCommentList(userId, postId);
        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    @GetMapping(value = "/details/{commentId}")
    public ResponseEntity<CommentResponse> getCommentById(@PathVariable(value = "commentId") Long commentId) {
        CommentResponse foundedComment = commentService.getCommentById(commentId);
        if (foundedComment != null) {
            return new ResponseEntity<>(foundedComment, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/update/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable(value = "commentId") Long commentId,
            @RequestBody CommentUpdateRequest request) {
        Boolean existCommentById = commentService.existsById(commentId);
        if (existCommentById) {
            CommentResponse updatedComment = commentService.updateComment(commentId, request);
            return new ResponseEntity<>(updatedComment, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/delete/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable(value = "commentId") Long commentId) {
        Boolean existCommentById = commentService.existsById(commentId);
        if (existCommentById) {
            commentService.deleteComment(commentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
