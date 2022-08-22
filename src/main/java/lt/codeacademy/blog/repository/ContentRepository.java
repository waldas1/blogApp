package lt.codeacademy.blog.repository;

import lt.codeacademy.blog.entity.ContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ContentRepository extends JpaRepository<ContentEntity, UUID> {

    List<ContentEntity> findAllByPicURL(String picURL);
}
