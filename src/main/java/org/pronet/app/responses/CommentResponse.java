package org.pronet.app.responses;

import lombok.Data;
import org.pronet.app.entities.Comment;

import java.util.Date;

@Data
public class CommentResponse {
    private Long id;
    private String text;
    private Long userId;
    private Long postId;
    private Date createdDate;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.text = comment.getText();
        this.userId = comment.getUser().getId();
        this.postId = comment.getPost().getId();
        this.createdDate = comment.getCreatedDate();
    }
}
