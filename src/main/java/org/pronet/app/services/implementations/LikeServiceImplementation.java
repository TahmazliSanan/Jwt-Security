package org.pronet.app.services.implementations;

import lombok.RequiredArgsConstructor;
import org.pronet.app.entities.Like;
import org.pronet.app.entities.Post;
import org.pronet.app.entities.User;
import org.pronet.app.repositories.LikeRepository;
import org.pronet.app.repositories.PostRepository;
import org.pronet.app.repositories.UserRepository;
import org.pronet.app.requests.like.LikeCreateRequest;
import org.pronet.app.services.LikeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikeServiceImplementation implements LikeService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    @Override
    public Like createLike(LikeCreateRequest request) {
        User foundedUser = userRepository
                .findById(request.getUserId())
                .orElse(null);
        Post foundedPost = postRepository
                .findById(request.getPostId())
                .orElse(null);
        if (foundedUser != null && foundedPost != null) {
            Like Like = new Like();
            Like.setId(request.getId());
            Like.setUser(foundedUser);
            Like.setPost(foundedPost);
            return likeRepository.save(Like);
        }
        return null;
    }

    @Override
    public List<Like> getLikeList(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()) {
            return likeRepository.findAllByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return likeRepository.findAllByUserId(userId.get());
        } else if (postId.isPresent()) {
            return likeRepository.findAllByPostId(postId.get());
        }
        return likeRepository.findAll();
    }

    @Override
    public Like getLikeById(Long LikeId) {
        return likeRepository
                .findById(LikeId)
                .orElse(null);
    }

    @Override
    public void deleteLike(Long LikeId) {
        likeRepository.deleteById(LikeId);
    }
}
