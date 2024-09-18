package org.pronet.app.services.implementations;

import lombok.RequiredArgsConstructor;
import org.pronet.app.entities.Post;
import org.pronet.app.entities.User;
import org.pronet.app.repositories.PostRepository;
import org.pronet.app.repositories.UserRepository;
import org.pronet.app.requests.post.PostCreateRequest;
import org.pronet.app.requests.post.PostUpdateRequest;
import org.pronet.app.services.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostServiceImplementation implements PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public Post createPost(PostCreateRequest request) {
        User foundedUser = userRepository
                .findById(request.getUserId())
                .orElse(null);
        if (foundedUser != null) {
            Post post = new Post();
            post.setId(request.getId());
            post.setTitle(request.getTitle());
            post.setText(request.getText());
            post.setUser(foundedUser);
            return postRepository.save(post);
        }
        return null;
    }

    @Override
    public List<Post> getPostList(Optional<Long> userId) {
        if (userId.isPresent()) {
            return postRepository.findAllByUserId(userId.get());
        }
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Long postId) {
        return postRepository
                .findById(postId)
                .orElse(null);
    }

    @Override
    public Post updatePost(Long postId, PostUpdateRequest request) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setTitle(request.getTitle());
            post.setText(request.getText());
            return postRepository.save(post);
        }
        return null;
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
