package lt.codeacademy.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.codeacademy.blog.entity.CommentEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    private LocalDateTime date;

    public Comment(String comment, LocalDateTime date) {
        this.comment = comment;
        this.date = date;
    }

    public static Comment convert(CommentEntity entity) {
        return new Comment(entity.getComment(),
                entity.getDate());
    }
}
