package lt.codeacademy.blog.exception;

public class PictureNotFoundException extends RuntimeException{
    private final Long pictureId;

    public PictureNotFoundException(Long pictureId) {
        this.pictureId = pictureId;
    }

    public Long getPictureId(){
        return pictureId;
    }
}
