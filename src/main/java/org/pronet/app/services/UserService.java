package org.pronet.app.services;

import org.pronet.app.requests.authentication.RegisterRequest;
import org.pronet.app.requests.user.UserUpdateRequest;
import org.pronet.app.responses.UserActivityResponse;
import org.pronet.app.responses.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(RegisterRequest request);
    List<UserResponse> getUserList();
    UserResponse getUserById(Long userId);
    UserResponse getUserByUsername(String username);
    Boolean existsUserById(Long userId);
    Boolean existsUserByUsername(String username);
    UserResponse updateUser(Long userId, UserUpdateRequest user);
    void deleteUser(Long userId);
    UserActivityResponse getUserActivity(Long userId);
}
