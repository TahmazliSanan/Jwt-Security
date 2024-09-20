package org.pronet.app.repositories;

import org.pronet.app.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByUserIdAndPostId(Long userId, Long postId);
    List<Comment> findAllByUserId(Long userId);
    List<Comment> findAllByPostId(Long postId);
    boolean existsById(Long commentId);
}
