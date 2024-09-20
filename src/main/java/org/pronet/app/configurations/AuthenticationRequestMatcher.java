package org.pronet.app.configurations;

public class AuthenticationRequestMatcher {
    public static final String[] NON_AUTH_ROUTES = {
            "/post/list",
            "/comment/list",
            "/auth/register",
            "/auth/login"
    };

    public static final String[] USER_AUTH_ROUTES = {
            "/post/create",
            "/post/details/{postId}",
            "/post/update/{postId}",
            "/post/delete/{postId}",
            "/comment/create",
            "/comment/details/{commentId}",
            "/comment/update/{commentId}",
            "/comment/delete/{commentId}",
            "/like/create",
            "/like/details/{likeId}",
            "/like/delete/{likeId}",
    };
}
