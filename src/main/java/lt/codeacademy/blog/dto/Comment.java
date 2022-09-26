package lt.codeacademy.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.codeacademy.blog.entity.CommentEntity;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private UUID id;
    @NotBlank
    private String comment;
    private LocalDate date;
    private Content content;
    private User user;

    public static Comment convert(CommentEntity entity) {
        Content content = Content.convert(entity.getContentEntity());
        User user = User.convert(entity.getUser());
        return new Comment(entity.getId(),
                entity.getComment(),
                entity.getDate(),
                content,
                user);
    }
}
