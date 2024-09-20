package org.pronet.app.services;

import org.pronet.app.requests.post.PostCreateRequest;
import org.pronet.app.requests.post.PostUpdateRequest;
import org.pronet.app.responses.PostResponse;

import java.util.List;
import java.util.Optional;

public interface PostService {
    PostResponse createPost(PostCreateRequest request);
    List<PostResponse> getPostList(Optional<Long> userId);
    PostResponse getPostById(Long postId);
    PostResponse updatePost(Long postId, PostUpdateRequest request);
    void deletePost(Long postId);
    Boolean existsById(Long postId);
}
