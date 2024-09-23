package org.pronet.app.requests.authentication;

import lombok.Data;

@Data
public class RegisterRequest {
    private Long id;
    private String username;
    private String password;
}
