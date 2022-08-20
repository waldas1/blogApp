package lt.codeacademy.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.codeacademy.blog.dto.Pictures;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pictures")
public class PictureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String urlPic;
    private LocalDateTime date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CommentEntity> commentEntityList;

    public PictureEntity(String urlPic, LocalDateTime date) {
        this.urlPic = urlPic;
        this.date = date;
    }

    public static PictureEntity convert(Pictures pictures) {
        return new PictureEntity(pictures.getUrlPic(),
                pictures.getDate());
    }
}
