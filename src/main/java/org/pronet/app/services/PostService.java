package org.pronet.app.services;

import org.pronet.app.entities.Post;
import org.pronet.app.requests.post.PostCreateRequest;
import org.pronet.app.requests.post.PostUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Post createPost(PostCreateRequest request);
    List<Post> getPostList(Optional<Long> userId);
    Post getPostById(Long postId);
    Post updatePost(Long postId, PostUpdateRequest request);
    void deletePost(Long postId);
}
