package lt.codeacademy.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.codeacademy.blog.entity.ContentEntity;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Content {

    private UUID id;
    @NotBlank
    private String picURL;
    private String picComments;
    private LocalDate date;
    private List<Comment> comments;

    public Content(UUID id, String picURL, String picComment, List<Comment> comments) {
        this.id = id;
        this.picURL = picURL;
        this.picComments = picComment;
        this.comments = comments;
    }

    public static Content convert(ContentEntity entity) {
        List<Comment> comments = entity.getComments().stream()
                .map(Comment::convert)
                .toList();

        return new Content(entity.getId(),
                entity.getPicURL(),
                entity.getPicComment(),
                comments);
    }
}
