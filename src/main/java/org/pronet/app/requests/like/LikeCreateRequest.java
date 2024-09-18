package org.pronet.app.requests.like;

import lombok.Data;

@Data
public class LikeCreateRequest {
    private Long id;
    private Long userId;
    private Long postId;
}
