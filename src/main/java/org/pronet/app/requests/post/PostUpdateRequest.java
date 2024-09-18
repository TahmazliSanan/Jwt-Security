package org.pronet.app.requests.post;

import lombok.Data;

@Data
public class PostUpdateRequest {
    private String title;
    private String text;
}
