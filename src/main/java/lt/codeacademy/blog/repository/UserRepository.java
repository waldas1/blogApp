package lt.codeacademy.blog.repository;

import lt.codeacademy.blog.Enum.Role;
import lt.codeacademy.blog.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByRole(Role role);

}
