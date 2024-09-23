package org.pronet.app.configurations;

public class AuthenticationRequestMatcher {
    public static final String[] NON_AUTH_ROUTES = {
            "/post/list",
            "/comment/list",
            "/like/list",
            "/auth/register",
            "/auth/login"
    };
}
