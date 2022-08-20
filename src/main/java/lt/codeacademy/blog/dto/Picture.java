package lt.codeacademy.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.codeacademy.blog.entity.PictureEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String urlPic;
    @NotBlank
    private LocalDateTime date;

    public Picture(String urlPic, LocalDateTime date) {
        this.urlPic = urlPic;
        this.date = date;
    }

    public static Picture convert(PictureEntity entity){
        return new Picture(entity.getUrlPic(),
                entity.getDate());
    }
}
