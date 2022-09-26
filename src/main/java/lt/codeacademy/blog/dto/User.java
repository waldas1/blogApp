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
    private Set<Role> roles;
    private List<Comment> comments;

    public User(UUID id, String name, String surname, String username, String password, String country, int age, String email, Set<Role> roles) {
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

    public static User convert(UserEntity entity) {
        Set<Role> roles = entity.getRoles().stream()
                .map(Role::convert)
                .collect(Collectors.toSet());

        return new User(entity.getId(),
                entity.getName(),
                entity.getSurname(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getCountry(),
                entity.getAge(),
                entity.getEmail(),
                roles);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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
