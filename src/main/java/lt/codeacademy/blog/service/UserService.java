package lt.codeacademy.blog.service;

import lt.codeacademy.blog.dto.Role;
import lt.codeacademy.blog.dto.User;
import lt.codeacademy.blog.entity.UserEntity;
import lt.codeacademy.blog.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(Set.of(new Role(UUID.fromString("7f74bb02-9f14-43ce-8b28-8c0c889d1558"), "USER")));

        userRepository.save(UserEntity.convert(user));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User by username: %s does not exist", username)));
        return User.convert(user);
    }

}
