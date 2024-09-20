package org.pronet.app.controllers;

import lombok.RequiredArgsConstructor;
import org.pronet.app.entities.User;
import org.pronet.app.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/list")
    public List<User> getUserList() {
        return userService.getUserList();
    }

    @GetMapping(value = "/details/{userId}")
    public User getUserById(@PathVariable(value = "userId") Long userId) {
        return userService.getUserById(userId);
    }

    @PutMapping(value = "/update/{userId}")
    public User updateUser(
            @PathVariable(value = "userId") Long userId,
            @RequestBody User user) {
        return userService.updateUser(userId, user);
    }

    @DeleteMapping(value = "/delete/{userId}")
    public void deleteUser(@PathVariable(value = "userId") Long userId) {
        userService.deleteUser(userId);
    }
}
