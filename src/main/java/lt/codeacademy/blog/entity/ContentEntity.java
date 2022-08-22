package lt.codeacademy.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.codeacademy.blog.dto.Content;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Contents")
public class ContentEntity {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "VARCHAR(36)", updatable = false)
    @Type(type = "uuid-char")
    private UUID id;
    @NotBlank
    private String picURL;
    private String picComment;

    private LocalDate date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CommentEntity> comments;

    public ContentEntity(UUID id, String picURL, String picComment, LocalDate date, List<CommentEntity> comments) {
        this.picURL = picURL;
        this.id = id;
        this.picComment = picComment;
        this.date = date;
        this.comments = comments;
    }

    public static ContentEntity convert(Content content) {
        List<CommentEntity> comments = content.getComments().stream()
                .map(CommentEntity::convert)
                .toList();

        return new ContentEntity(content.getId(),
                content.getPicURL(),
                content.getPicComments(),
                content.getDate(),
                comments);
    }
}
