package org.pronet.app.controllers;

import lombok.RequiredArgsConstructor;
import org.pronet.app.requests.user.LoginRequest;
import org.pronet.app.requests.user.RegisterRequest;
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
    public String login(@RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "Bearer " + jwtTokenProvider.generateToken(authentication);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        Boolean existUserByUsername = userService.existsUserByUsername(request.getUsername());
        if (existUserByUsername) {
            return new ResponseEntity<>("User is already exist!", HttpStatus.BAD_REQUEST);
        }
        userService.createUser(request);
        return new ResponseEntity<>("User is registered successfully!", HttpStatus.CREATED);
    }
}
