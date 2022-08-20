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
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@CompareFields(first = "password", second = "repeatPassword")
public class User implements UserDetails {
    private Long id;
    @NotBlank
    private String name;
    private String surname;
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String repeatPassword;
    private String country;
    private String city;
    private String street;
    private String postCode;
    private int age;
    private String email;
    private Set<Role> role;

    public User(String name, String surname, String username, String password, String repeatPassword, String country, String city, String street, String postCode, int age, String email, Set<Role> role) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.country = country;
        this.city = city;
        this.street = street;
        this.postCode = postCode;
        this.age = age;
        this.email = email;
        this.role = role;
    }

    public static User convert(UserEntity entity) {
        Set<Role> role = entity.getRole().stream()
                .map(Role::convert)
                .collect(Collectors.toSet());

        return new User(entity.getName(),
                entity.getSurname(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getRepeatPassword(),
                entity.getCountry(),
                entity.getCity(),
                entity.getStreet(),
                entity.getPostCode(),
                entity.getAge(),
                entity.getEmail(),
                role);
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
