package org.pronet.app.services;

import org.pronet.app.entities.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getUserList();
    User getUserById(Long userId);
    User updateUser(Long userId, User user);
    void deleteUser(Long userId);
}
