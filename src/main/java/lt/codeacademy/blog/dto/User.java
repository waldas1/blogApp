package lt.codeacademy.blog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.codeacademy.blog.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String role;
    private int age;
    private LocalDateTime registerDate;

    public User(String name, String surname, String username, String password, String role, int age, LocalDateTime birthDate) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.role= role;
        this.age = age;
        this.registerDate = birthDate;
    }

    public static User convert(UserEntity entity) {
        return new User(entity.getName(),
                entity.getSurname(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getRole(),
                entity.getAge(),
                LocalDateTime.now()
        );
    }
}
