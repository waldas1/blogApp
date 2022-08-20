package lt.codeacademy.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.codeacademy.blog.dto.Comment;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String comment;
    @NotBlank
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "picture_id")
    private PictureEntity pictureEntity;

    public CommentEntity(String comment, LocalDateTime date) {
        this.comment = comment;
        this.date = date;
    }

    public static CommentEntity convert(Comment comment) {
        return new CommentEntity(comment.getComment(),
                comment.getDate());
    }
}
