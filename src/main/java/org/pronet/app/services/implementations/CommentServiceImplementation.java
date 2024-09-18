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
import org.pronet.app.services.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImplementation implements CommentService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public Comment createComment(CommentCreateRequest request) {
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
            return commentRepository.save(comment);
        }
        return null;
    }

    @Override
    public List<Comment> getCommentList(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()) {
            return commentRepository.findAllByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findAllByUserId(userId.get());
        } else if (postId.isPresent()) {
            return commentRepository.findAllByPostId(postId.get());
        }
        return commentRepository.findAll();
    }

    @Override
    public Comment getCommentById(Long commentId) {
        return commentRepository
                .findById(commentId)
                .orElse(null);
    }

    @Override
    public Comment updateComment(Long commentId, CommentUpdateRequest request) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            comment.setText(request.getText());
            return commentRepository.save(comment);
        }
        return null;
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
