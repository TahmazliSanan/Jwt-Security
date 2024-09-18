package org.pronet.app.controllers;

import lombok.RequiredArgsConstructor;
import org.pronet.app.entities.Post;
import org.pronet.app.requests.post.PostCreateRequest;
import org.pronet.app.requests.post.PostUpdateRequest;
import org.pronet.app.responses.PostResponse;
import org.pronet.app.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/post")
public class PostController {
    private final PostService postService;

    @PostMapping(value = "/create")
    public Post createPost(@RequestBody PostCreateRequest request) {
        return postService.createPost(request);
    }

    @GetMapping(value = "/list")
    public List<PostResponse> getPostList(@RequestParam Optional<Long> userId) {
        return postService.getPostList(userId);
    }

    @GetMapping(value = "/details/{postId}")
    public PostResponse getPostById(@PathVariable(value = "postId") Long postId) {
        return postService.getPostById(postId);
    }

    @PutMapping(value = "/update/{postId}")
    public Post updatePost(
            @PathVariable(value = "postId") Long postId,
            @RequestBody PostUpdateRequest request) {
        return postService.updatePost(postId, request);
    }

    @DeleteMapping(value = "/delete/{postId}")
    public void deletePost(@PathVariable(value = "postId") Long postId) {
        postService.deletePost(postId);
    }
}
