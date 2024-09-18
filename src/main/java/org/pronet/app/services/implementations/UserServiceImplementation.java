package org.pronet.app.services.implementations;

import lombok.RequiredArgsConstructor;
import org.pronet.app.entities.User;
import org.pronet.app.repositories.UserRepository;
import org.pronet.app.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository
                .findById(userId)
                .orElse(null);
    }

    @Override
    public User updateUser(Long userId, User user) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User foundedUser = optionalUser.get();
            foundedUser.setUsername(user.getUsername());
            foundedUser.setPassword(user.getPassword());
            userRepository.save(foundedUser);
            return foundedUser;
        }
        return null;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
