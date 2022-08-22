package lt.codeacademy.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.codeacademy.blog.dto.Content;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public ContentEntity(UUID id, String picURL, String comment) {
        this.picURL = picURL;
        this.id = id;
        this.comment = comment;
    }

    public static ContentEntity convert(Content comment) {
        return new ContentEntity(comment.getId(),
                comment.getPicURL(),
                comment.getComment());
    }
}
