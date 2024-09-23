package org.pronet.app.services;

import org.pronet.app.requests.comment.CommentCreateRequest;
import org.pronet.app.requests.comment.CommentUpdateRequest;
import org.pronet.app.responses.CommentResponse;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    CommentResponse createComment(CommentCreateRequest request);
    List<CommentResponse> getCommentList(Optional<Long> userId, Optional<Long> postId);
    CommentResponse getCommentById(Long commentId);
    CommentResponse updateComment(Long commentId, CommentUpdateRequest request);
    void deleteComment(Long commentId);
    Boolean existsById(Long commentId);
}
