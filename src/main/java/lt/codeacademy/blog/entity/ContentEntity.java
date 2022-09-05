package lt.codeacademy.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.codeacademy.blog.dto.Content;
import org.hibernate.annotations.Type;

import javax.persistence.*;
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
    @Column(nullable = false)
    private String picURL;
    @Column(nullable = false)
    private String pic_Comment;
    @Column(nullable = false)
    private LocalDate date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CommentEntity> comments;

    public ContentEntity(UUID id, String picURL, String pic_Comment, LocalDate date) {
        this.picURL = picURL;
        this.id = id;
        this.pic_Comment = pic_Comment;
        this.date = date;
    }

    public static ContentEntity convert(Content content) {
        return new ContentEntity(content.getId(),
                content.getPicURL(),
                content.getPic_Comment(),
                content.getDate());
    }
}
