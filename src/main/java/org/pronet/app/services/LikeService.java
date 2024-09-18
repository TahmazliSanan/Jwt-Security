package org.pronet.app.services;

import org.pronet.app.entities.Like;
import org.pronet.app.requests.like.LikeCreateRequest;

import java.util.List;
import java.util.Optional;

public interface LikeService {
    Like createLike(LikeCreateRequest request);
    List<Like> getLikeList(Optional<Long> userId, Optional<Long> postId);
    Like getLikeById(Long likeId);
    void deleteLike(Long likeId);
}
