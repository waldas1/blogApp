package lt.codeacademy.blog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.codeacademy.blog.dto.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(columnDefinition = "varchar(36)", updatable = false)
//    @Type(type = "id-char")
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String role;
    private int age;
    private LocalDateTime registerDate;

    public UserEntity(String name, String surname, String username, String password, String role, int age, LocalDateTime registerDate) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.role = role;
        this.age = age;
        this.registerDate = registerDate;
    }

    public static UserEntity convert(User user) {
        return new UserEntity(user.getName(),
                user.getSurname(),
                user.getUsername(),
                user.getPassword(),
                user.getRole(),
                user.getAge(),
                LocalDateTime.now()
        );
    }
}
