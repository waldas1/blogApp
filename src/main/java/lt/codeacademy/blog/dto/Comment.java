package lt.codeacademy.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.codeacademy.blog.entity.CommentEntity;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private UUID id;
    private String comment;
    private LocalDate date;

    public static Comment convert(CommentEntity entity){
        return new Comment(entity.getId(),
                entity.getComment(),
                entity.getDate());
    }
}
