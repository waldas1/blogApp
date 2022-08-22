package lt.codeacademy.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.codeacademy.blog.entity.ContentEntity;
import lt.codeacademy.blog.entity.UserEntity;
import lt.codeacademy.blog.validator.annotation.CompareFields;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.OneToMany;
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

    public User(UUID id, String name, String surname, String username, String password, String country, int age, String email, Set<Role> role, List<Content> contents) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.country = country;
        this.age = age;
        this.email = email;
        this.role = role;
        this.content = contents;
    }

    public static User convert(UserEntity entity) {
        Set<Role> role = entity.getRole().stream()
                .map(Role::convert)
                .collect(Collectors.toSet());

        List<Content> contents = entity.getContent().stream()
                .map(Content::convert)
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
                contents);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public String getFullName() {
        return name + " " + surname;
    }
}
