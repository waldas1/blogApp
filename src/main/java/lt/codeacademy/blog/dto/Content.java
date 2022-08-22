package lt.codeacademy.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.codeacademy.blog.entity.ContentEntity;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Content {

    private UUID id;
    @NotBlank
    private String picURL;
    private String comment;

    public static Content convert(ContentEntity entity) {
        return new Content(entity.getId(),
                entity.getPicURL(),
                entity.getComment());
    }
}
