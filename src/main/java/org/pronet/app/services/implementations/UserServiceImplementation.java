package org.pronet.app.services.implementations;

import lombok.RequiredArgsConstructor;
import org.pronet.app.entities.User;
import org.pronet.app.repositories.CommentRepository;
import org.pronet.app.repositories.PostRepository;
import org.pronet.app.repositories.UserRepository;
import org.pronet.app.requests.authentication.RegisterRequest;
import org.pronet.app.requests.user.UserUpdateRequest;
import org.pronet.app.responses.*;
import org.pronet.app.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse createUser(RegisterRequest request) {
        User user = new User();
        user.setId(request.getId());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User createdUser = userRepository.save(user);
        return new UserResponse(createdUser);
    }

    @Override
    public List<UserResponse> getUserList() {
        List<User> userList = userRepository.findAll();
        return userList
                .stream()
                .map(UserResponse::new)
                .toList();
    }

    @Override
    public UserResponse getUserById(Long userId) {
        User foundedUser = userRepository
                .findById(userId)
                .orElse(null);
        if (foundedUser != null) {
            return new UserResponse(foundedUser);
        }
        return null;
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        User foundedUser = userRepository.findByUsernameContainingIgnoreCase(username);
        if (foundedUser != null) {
            return new UserResponse(foundedUser);
        }
        return null;
    }

    @Override
    public Boolean existsUserById(Long userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public Boolean existsUserByUsername(String username) {
        return userRepository.existsByUsernameContainingIgnoreCase(username);
    }

    @Override
    public UserResponse updateUser(Long userId, UserUpdateRequest request) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User foundedUser = optionalUser.get();
            foundedUser.setUsername(request.getUsername());
            User updatedUser = userRepository.save(foundedUser);
            return new UserResponse(updatedUser);
        }
        return null;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserActivityResponse getUserActivity(Long userId) {
        List<PostResponse> postList = postRepository
                .findAllByUserId(userId)
                .stream()
                .map(PostResponse::new)
                .toList();
        List<CommentResponse> commentList = commentRepository
                .findAllByUserId(userId)
                .stream()
                .map(CommentResponse::new)
                .toList();
        UserActivityResponse userActivity = new UserActivityResponse();
        userActivity.setUserId(userId);
        userActivity.setPostList(postList);
        userActivity.setCommentList(commentList);
        return userActivity;
    }
}
