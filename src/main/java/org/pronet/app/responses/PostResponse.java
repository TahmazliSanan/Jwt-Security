package org.pronet.app.responses;

import lombok.Data;
import org.pronet.app.entities.Post;

import java.util.Date;
import java.util.List;

@Data
public class PostResponse {
    private Long id;
    private String title;
    private String text;
    private Long userId;
    private String username;
    private List<LikeResponse> likes;
    private Date createdDate;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.text = post.getText();
        this.userId = post.getUser().getId();
        this.username = post.getUser().getUsername();
        this.createdDate = post.getCreatedDate();
    }

    public PostResponse(Post post, List<LikeResponse> likes) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.text = post.getText();
        this.userId = post.getUser().getId();
        this.username = post.getUser().getUsername();
        this.createdDate = post.getCreatedDate();
        this.likes = likes;
    }
}
