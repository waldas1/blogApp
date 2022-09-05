package lt.codeacademy.blog.exception;

import java.util.UUID;

public class CommentNotFoundException extends RuntimeException{
    private final UUID commentId;

    public CommentNotFoundException(UUID commentId) {
        this.commentId = commentId;
    }

    public UUID getContentId(){
        return commentId;
    }
}
