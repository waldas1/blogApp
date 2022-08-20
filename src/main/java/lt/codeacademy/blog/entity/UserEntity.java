package lt.codeacademy.blog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.codeacademy.blog.dto.Pictures;
import lt.codeacademy.blog.dto.User;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String repeatPassword;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String postCode;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CommentEntity> commentEntityList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PictureEntity> pictureEntityList;

    public UserEntity(String name, String surname, String username, String password, String repeatPassword, String country, String city, String street, String postCode, int age, String email, Set<RoleEntity> role) {
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

    public static UserEntity convert(User user) {
        Set<RoleEntity> role = user.getRole().stream()
                .map(RoleEntity::convert)
                .collect(Collectors.toSet());


        return new UserEntity(user.getName(),
                user.getSurname(),
                user.getUsername(),
                user.getPassword(),
                user.getRepeatPassword(),
                user.getCountry(),
                user.getCity(),
                user.getStreet(),
                user.getPostCode(),
                user.getAge(),
                user.getEmail(),
                role);
    }
}
