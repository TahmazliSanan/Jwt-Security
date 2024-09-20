package org.pronet.app.services;

import org.pronet.app.entities.Like;
import org.pronet.app.requests.like.LikeCreateRequest;
import org.pronet.app.responses.LikeResponse;

import java.util.List;
import java.util.Optional;

public interface LikeService {
    Like createLike(LikeCreateRequest request);
    List<LikeResponse> getLikeList(Optional<Long> userId, Optional<Long> postId);
    LikeResponse getLikeById(Long likeId);
    Boolean existsById(Long likeId);
    void deleteLike(Long likeId);
}
