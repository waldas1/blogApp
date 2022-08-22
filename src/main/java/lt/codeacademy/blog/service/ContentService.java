package lt.codeacademy.blog.service;

import lt.codeacademy.blog.dto.Content;
import lt.codeacademy.blog.entity.ContentEntity;
import lt.codeacademy.blog.exception.ContentNotFoundException;
import lt.codeacademy.blog.repository.ContentRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

@Service
public class ContentService {

    private final ContentRepository contentRepository;

    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public Content getContent(UUID id) {
        return contentRepository.findById(id)
                .map(Content::convert)
                .orElseThrow(() -> new ContentNotFoundException(id));
    }

    public Page<Content> getContents(Pageable pageable) {
        return contentRepository.findAll(pageable)
                .map(Content::convert);
    }

    public List<Content> getCommentsByPicUrl(String picUrl) {
        return contentRepository.findAllByPicURL(picUrl).stream()
                .map(Content::convert)
                .toList();
    }

    public void updateContent(Content content) {
        contentRepository.save(ContentEntity.convert(content));
    }

    public void deleteContent(UUID id) {
        contentRepository.deleteById(id);
    }
}
