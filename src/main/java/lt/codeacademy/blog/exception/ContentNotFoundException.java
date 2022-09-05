package lt.codeacademy.blog.exception;

import java.util.UUID;

public class ContentNotFoundException extends RuntimeException{
    private final UUID contentId;

    public ContentNotFoundException(UUID contentId) {
        this.contentId = contentId;
    }

    public UUID getContentId(){
        return contentId;
    }
}
