package org.pronet.app.repositories;

import org.pronet.app.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findAllByUserIdAndPostId(Long userId, Long postId);
    List<Like> findAllByUserId(Long userId);
    List<Like> findAllByPostId(Long postId);
    boolean existsById(Long likeId);
}
