package org.pronet.app.services;

import org.pronet.app.entities.User;
import org.pronet.app.requests.user.RegisterRequest;

import java.util.List;

public interface UserService {
    void createUser(RegisterRequest request);
    List<User> getUserList();
    User getUserById(Long userId);
    Boolean existsUserByUsername(String username);
    User updateUser(Long userId, User user);
    void deleteUser(Long userId);
}
