package lt.codeacademy.blog.service;

import lt.codeacademy.blog.Enum.Role;
import lt.codeacademy.blog.dto.User;
import lt.codeacademy.blog.entity.UserEntity;
import lt.codeacademy.blog.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.save(UserEntity.convert(user));
    }

    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(User::convert);
    }

    public List<User> getUserByRole(Role role){
        return userRepository.findByRole(role).stream()
                .map(User::convert)
                .toList();
    }
}
