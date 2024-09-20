package org.pronet.app.services;

import org.pronet.app.entities.Comment;
import org.pronet.app.requests.comment.CommentCreateRequest;
import org.pronet.app.requests.comment.CommentUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Comment createComment(CommentCreateRequest request);
    List<Comment> getCommentList(Optional<Long> userId, Optional<Long> postId);
    Comment getCommentById(Long commentId);
    Comment updateComment(Long commentId, CommentUpdateRequest request);
    void deleteComment(Long commentId);
    Boolean existsById(Long commentId);
}
