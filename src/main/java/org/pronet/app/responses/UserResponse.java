package org.pronet.app.responses;

import lombok.Data;
import org.pronet.app.entities.User;

@Data
public class UserResponse {
    private Long id;
    private String username;

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
