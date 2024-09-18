package org.pronet.app.controllers;

import lombok.RequiredArgsConstructor;
import org.pronet.app.entities.Comment;
import org.pronet.app.requests.comment.CommentCreateRequest;
import org.pronet.app.requests.comment.CommentUpdateRequest;
import org.pronet.app.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping(value = "/create")
    public Comment createComment(@RequestBody CommentCreateRequest request) {
        return commentService.createComment(request);
    }

    @GetMapping(value = "/list")
    public List<Comment> getCommentList(
            @RequestParam Optional<Long> userId,
            @RequestParam Optional<Long> postId) {
        return commentService.getCommentList(userId, postId);
    }

    @GetMapping(value = "/details/{commentId}")
    public Comment getCommentById(@PathVariable(value = "commentId") Long commentId) {
        return commentService.getCommentById(commentId);
    }

    @PutMapping(value = "/update/{commentId}")
    public Comment updateComment(
            @PathVariable(value = "commentId") Long commentId,
            @RequestBody CommentUpdateRequest request) {
        return commentService.updateComment(commentId, request);
    }

    @DeleteMapping(value = "/delete/{commentId}")
    public void deleteComment(@PathVariable(value = "commentId") Long commentId) {
        commentService.deleteComment(commentId);
    }
}
