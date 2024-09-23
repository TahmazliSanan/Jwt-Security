package org.pronet.app.services.implementations;

import lombok.RequiredArgsConstructor;
import org.pronet.app.entities.Like;
import org.pronet.app.entities.Post;
import org.pronet.app.entities.User;
import org.pronet.app.repositories.LikeRepository;
import org.pronet.app.repositories.PostRepository;
import org.pronet.app.repositories.UserRepository;
import org.pronet.app.requests.like.LikeCreateRequest;
import org.pronet.app.responses.LikeResponse;
import org.pronet.app.services.LikeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<LikeResponse> getLikeList(Optional<Long> userId, Optional<Long> postId) {
        List<Like> likeList;
        if (userId.isPresent() && postId.isPresent()) {
            likeList = likeRepository.findAllByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            likeList = likeRepository.findAllByUserId(userId.get());
        } else if (postId.isPresent()) {
            likeList = likeRepository.findAllByPostId(postId.get());
        } else {
            likeList = likeRepository.findAll();
        }
        return likeList
                .stream()
                .map(LikeResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public LikeResponse getLikeById(Long likeId) {
        Like like = likeRepository
                .findById(likeId)
                .orElse(null);
        if (like != null) {
            return new LikeResponse(like);
        }
        return null;
    }

    @Override
    public Boolean existsById(Long likeId) {
        return likeRepository.existsById(likeId);
    }

    @Override
    public void deleteLike(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
