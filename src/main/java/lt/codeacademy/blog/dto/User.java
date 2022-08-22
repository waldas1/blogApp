package lt.codeacademy.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.codeacademy.blog.entity.UserEntity;
import lt.codeacademy.blog.validator.annotation.CompareFields;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@CompareFields(first = "password", second = "repeatPassword")
public class User implements UserDetails {

    private UUID id;
    @NotBlank
    private String name;
    private String surname;
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String repeatPassword;
    private String country;
    private int age;
    private String email;
    private Set<Role> role;
    private List<Content> content;
    private List<Comment> comments;

    public User(UUID id, String name, String surname, String username, String password, String country, int age, String email, Set<Role> role, List<Content> content,List<Comment> comments) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.country = country;
        this.age = age;
        this.email = email;
        this.role = role;
        this.content = content;
        this.comments = comments;
    }

    public static User convert(UserEntity entity) {
        Set<Role> role = entity.getRole().stream()
                .map(Role::convert)
                .collect(Collectors.toSet());

        List<Content> content = entity.getContent().stream()
                .map(Content::convert)
                .toList();

        List<Comment> comments = entity.getComments().stream()
                .map(Comment::convert)
                .toList();

        return new User(entity.getId(),
                entity.getName(),
                entity.getSurname(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getCountry(),
                entity.getAge(),
                entity.getEmail(),
                role,
                content,
                comments);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFullName() {
        return name + " " + surname;
    }
}
