package org.pronet.app.controllers;

import lombok.RequiredArgsConstructor;
import org.pronet.app.requests.post.PostCreateRequest;
import org.pronet.app.requests.post.PostUpdateRequest;
import org.pronet.app.responses.PostResponse;
import org.pronet.app.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/post")
public class PostController {
    private final PostService postService;

    @PostMapping(value = "/create")
    public ResponseEntity<PostResponse> createPost(@RequestBody PostCreateRequest request) {
        PostResponse createdPost = postService.createPost(request);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<PostResponse>> getPostList(@RequestParam Optional<Long> userId) {
        List<PostResponse> postList = postService.getPostList(userId);
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @GetMapping(value = "/details/{postId}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable(value = "postId") Long postId) {
        PostResponse foundedPost = postService.getPostById(postId);
        if (foundedPost != null) {
            return new ResponseEntity<>(foundedPost, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/update/{postId}")
    public ResponseEntity<PostResponse> updatePost(
            @PathVariable(value = "postId") Long postId,
            @RequestBody PostUpdateRequest request) {
        Boolean existPostById = postService.existsById(postId);
        if (existPostById) {
            PostResponse updatedPost = postService.updatePost(postId, request);
            return new ResponseEntity<>(updatedPost, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/delete/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable(value = "postId") Long postId) {
        Boolean existPostById = postService.existsById(postId);
        if (existPostById) {
            postService.deletePost(postId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
