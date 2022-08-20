package lt.codeacademy.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.codeacademy.blog.dto.Role;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Roles")
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public static RoleEntity convert(Role role) {
        return new RoleEntity(role.getId(), role.getName());
    }
}
