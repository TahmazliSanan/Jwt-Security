package org.pronet.app.controllers;

import lombok.RequiredArgsConstructor;
import org.pronet.app.requests.user.UserUpdateRequest;
import org.pronet.app.responses.UserActivityResponse;
import org.pronet.app.responses.UserResponse;
import org.pronet.app.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/list")
    public ResponseEntity<List<UserResponse>> getUserList() {
        List<UserResponse> userList = userService.getUserList();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping(value = "/details/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable(value = "userId") Long userId) {
        UserResponse userResponse = userService.getUserById(userId);
        if (userResponse != null) {
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/update/{userId}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable(value = "userId") Long userId,
            @RequestBody UserUpdateRequest request) {
        Boolean existsUserById = userService.existsUserById(userId);
        if (existsUserById) {
            UserResponse updatedUser = userService.updateUser(userId, request);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/delete/{userId}")
    public void deleteUser(@PathVariable(value = "userId") Long userId) {
        userService.deleteUser(userId);
    }

    @GetMapping(value = "/{userId}/activity")
    public ResponseEntity<UserActivityResponse> getUserActivity(@PathVariable(value = "userId") Long userId) {
        Boolean existsUserById = userService.existsUserById(userId);
        if (!existsUserById) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserActivityResponse userActivity = userService.getUserActivity(userId);
        return new ResponseEntity<>(userActivity, HttpStatus.OK);
    }
}
