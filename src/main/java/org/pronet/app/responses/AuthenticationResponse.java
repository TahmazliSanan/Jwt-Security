package org.pronet.app.responses;

import lombok.Data;
import org.pronet.app.entities.User;

@Data
public class AuthenticationResponse {
    private String message;
    private Long userId;

    public AuthenticationResponse(String message, User user) {
        this.message = message;
        this.userId = user.getId();
    }
}
