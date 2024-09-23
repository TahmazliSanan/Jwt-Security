package org.pronet.app.controllers;

import lombok.RequiredArgsConstructor;
import org.pronet.app.requests.authentication.LoginRequest;
import org.pronet.app.requests.authentication.RegisterRequest;
import org.pronet.app.responses.AuthenticationResponse;
import org.pronet.app.responses.UserResponse;
import org.pronet.app.security.JwtTokenProvider;
import org.pronet.app.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserResponse foundedUser = userService.getUserByUsername(request.getUsername());
        String token = "Bearer " + jwtTokenProvider.generateToken(authentication);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(token, foundedUser);
        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        AuthenticationResponse authenticationResponse;
        Boolean existUserByUsername = userService.existsUserByUsername(request.getUsername());
        if (existUserByUsername) {
            UserResponse foundedUser = userService.getUserById(request.getId());
            authenticationResponse = new AuthenticationResponse("User is already exist!", foundedUser);
            return new ResponseEntity<>(authenticationResponse, HttpStatus.BAD_REQUEST);
        }
        UserResponse userResponse = userService.createUser(request);
        authenticationResponse = new AuthenticationResponse("User is registered successfully!", userResponse);
        return new ResponseEntity<>(authenticationResponse, HttpStatus.CREATED);
    }
}
