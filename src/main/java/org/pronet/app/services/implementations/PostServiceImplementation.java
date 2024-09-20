package org.pronet.app.services.implementations;

import lombok.RequiredArgsConstructor;
import org.pronet.app.entities.Post;
import org.pronet.app.entities.User;
import org.pronet.app.repositories.PostRepository;
import org.pronet.app.repositories.UserRepository;
import org.pronet.app.requests.post.PostCreateRequest;
import org.pronet.app.requests.post.PostUpdateRequest;
import org.pronet.app.responses.LikeResponse;
import org.pronet.app.responses.PostResponse;
import org.pronet.app.services.LikeService;
import org.pronet.app.services.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostServiceImplementation implements PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final LikeService likeService;

    @Override
    public PostResponse createPost(PostCreateRequest request) {
        User foundedUser = userRepository
                .findById(request.getUserId())
                .orElse(null);
        if (foundedUser != null) {
            Post post = new Post();
            post.setId(request.getId());
            post.setTitle(request.getTitle());
            post.setText(request.getText());
            post.setUser(foundedUser);
            Post createdPost = postRepository.save(post);
            return new PostResponse(createdPost);
        }
        return null;
    }

    @Override
    public List<PostResponse> getPostList(Optional<Long> userId) {
        List<Post> postList;
        if (userId.isPresent()) {
            postList = postRepository.findAllByUserId(userId.get());
        } else {
            postList = postRepository.findAll();
        }
        return postList.stream().map(p -> {
            List<LikeResponse> likeList = likeService.getLikeList(Optional.empty(), Optional.of(p.getId()));
            return new PostResponse(p, likeList);
        }).collect(Collectors.toList());
    }

    @Override
    public PostResponse getPostById(Long postId) {
        Post post = postRepository
                .findById(postId)
                .orElse(null);
        List<LikeResponse> likeList = likeService.getLikeList(Optional.empty(), Optional.of(postId));
        if (post != null) {
            return new PostResponse(post, likeList);
        }
        return null;
    }

    @Override
    public PostResponse updatePost(Long postId, PostUpdateRequest request) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setTitle(request.getTitle());
            post.setText(request.getText());
            Post updatedPost = postRepository.save(post);
            return new PostResponse(updatedPost);
        }
        return null;
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public Boolean existsById(Long postId) {
        return postRepository.existsById(postId);
    }
}
