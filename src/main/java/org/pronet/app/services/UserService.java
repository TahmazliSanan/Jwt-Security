package org.pronet.app.services;

import org.pronet.app.entities.User;
import org.pronet.app.requests.user.RegisterRequest;
import org.pronet.app.responses.AuthenticationResponse;

import java.util.List;

public interface UserService {
    AuthenticationResponse createUser(RegisterRequest request);
    List<User> getUserList();
    User getUserById(Long userId);
    User getUserByUsername(String username);
    Boolean existsUserByUsername(String username);
    User updateUser(Long userId, User user);
    void deleteUser(Long userId);
}
