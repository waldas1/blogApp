package lt.codeacademy.blog.exception;

import java.util.UUID;

public class ContentNotFoundException extends RuntimeException{
    private final UUID contentId;

    public ContentNotFoundException(UUID pictureId) {
        this.contentId = pictureId;
    }

    public UUID getContentId(){
        return contentId;
    }
}
