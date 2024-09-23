package org.pronet.app.responses;

import lombok.Data;

import java.util.List;

@Data
public class UserActivityResponse {
    private Long id;
    private Long userId;
    private List<PostResponse> postList;
    private List<CommentResponse> commentList;
}
