package org.pronet.app.services;

import org.pronet.app.entities.Post;
import org.pronet.app.requests.post.PostCreateRequest;
import org.pronet.app.requests.post.PostUpdateRequest;
import org.pronet.app.responses.PostResponse;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Post createPost(PostCreateRequest request);
    List<PostResponse> getPostList(Optional<Long> userId);
    PostResponse getPostById(Long postId);
    Post updatePost(Long postId, PostUpdateRequest request);
    void deletePost(Long postId);
}
