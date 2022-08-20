package lt.codeacademy.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lt.codeacademy.blog.entity.RoleEntity;
import org.springframework.security.core.GrantedAuthority;


@Getter
@AllArgsConstructor
public class Role implements GrantedAuthority {

    private static final String ROLE = "Role_";

    private Long id;

    private String name;

    @Override
    public String getAuthority() {
        return ROLE + name;
    }

    public static Role convert(RoleEntity entity){
        return new Role(entity.getId(), entity.getName());
    }
}
