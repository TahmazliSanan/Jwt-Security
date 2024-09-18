package org.pronet.app.responses;

import lombok.Data;
import org.pronet.app.entities.Post;

@Data
public class PostResponse {
    private Long id;
    private String title;
    private String text;
    private Long userId;
    private String username;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.text = post.getText();
        this.userId = post.getUser().getId();
        this.username = post.getUser().getUsername();
    }
}
