package lt.codeacademy.blog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.codeacademy.blog.dto.User;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "VARCHAR(36)", updatable = false)
    @Type(type = "uuid-char")
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ContentEntity> content;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CommentEntity> comments;

    public UserEntity(UUID id, String name, String surname, String username, String password, String country, int age, String email, Set<RoleEntity> roles) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.country = country;
        this.age = age;
        this.email = email;
        this.roles = roles;

    }

    public static UserEntity convert(User user) {
        Set<RoleEntity> roles = user.getRoles().stream()
                .map(RoleEntity::convert)
                .collect(Collectors.toSet());

        return new UserEntity(user.getId(),
                user.getName(),
                user.getSurname(),
                user.getUsername(),
                user.getPassword(),
                user.getCountry(),
                user.getAge(),
                user.getEmail(),
                roles);
    }
}
