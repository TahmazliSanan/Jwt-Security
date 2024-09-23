package org.pronet.app.responses;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private String message;
    private Long userId;

    public AuthenticationResponse(String message, UserResponse user) {
        this.message = message;
        this.userId = user.getId();
    }
}
