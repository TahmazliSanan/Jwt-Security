package org.pronet.app.services.implementations;

import lombok.RequiredArgsConstructor;
import org.pronet.app.entities.Comment;
import org.pronet.app.entities.Post;
import org.pronet.app.entities.User;
import org.pronet.app.repositories.CommentRepository;
import org.pronet.app.repositories.PostRepository;
import org.pronet.app.repositories.UserRepository;
import org.pronet.app.requests.comment.CommentCreateRequest;
import org.pronet.app.requests.comment.CommentUpdateRequest;
import org.pronet.app.responses.CommentResponse;
import org.pronet.app.services.CommentService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentServiceImplementation implements CommentService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public CommentResponse createComment(CommentCreateRequest request) {
        User foundedUser = userRepository
                .findById(request.getUserId())
                .orElse(null);
        Post foundedPost = postRepository
                .findById(request.getPostId())
                .orElse(null);
        if (foundedUser != null && foundedPost != null) {
            Comment comment = new Comment();
            comment.setId(request.getId());
            comment.setText(request.getText());
            comment.setUser(foundedUser);
            comment.setPost(foundedPost);
            comment.setCreatedDate(new Date());
            Comment createdComment = commentRepository.save(comment);
            return new CommentResponse(createdComment);
        }
        return null;
    }

    @Override
    public List<CommentResponse> getCommentList(Optional<Long> userId, Optional<Long> postId) {
        List<Comment> commentList;
        if (userId.isPresent() && postId.isPresent()) {
            commentList = commentRepository.findAllByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            commentList = commentRepository.findAllByUserId(userId.get());
        } else if (postId.isPresent()) {
            commentList = commentRepository.findAllByPostId(postId.get());
        } else {
            commentList = commentRepository.findAll();
        }
        return commentList
                .stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public CommentResponse getCommentById(Long commentId) {
        Comment comment = commentRepository
                .findById(commentId)
                .orElse(null);
        if (comment != null) {
            return new CommentResponse(comment);
        }
        return null;
    }

    @Override
    public CommentResponse updateComment(Long commentId, CommentUpdateRequest request) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            comment.setText(request.getText());
            Comment updatedComment = commentRepository.save(comment);
            return new CommentResponse(updatedComment);
        }
        return null;
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public Boolean existsById(Long commentId) {
        return commentRepository.existsById(commentId);
    }
}
