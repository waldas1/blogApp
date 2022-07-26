package lt.codeacademy.blog.service;

import lt.codeacademy.blog.dto.User;
import lt.codeacademy.blog.entity.UserEntity;
import lt.codeacademy.blog.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void createUser(User user){
        userRepository.save(UserEntity.convert(user));
    }
}
