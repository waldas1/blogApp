package lt.codeacademy.blog.service;

import lt.codeacademy.blog.dto.User;
import lt.codeacademy.blog.entity.UserEntity;
import lt.codeacademy.blog.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User by username: %s does not exist", username)));
        return User.convert(user);
    }

}
