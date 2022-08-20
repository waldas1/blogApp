package lt.codeacademy.blog.service;

import lt.codeacademy.blog.dto.Picture;
import lt.codeacademy.blog.exception.PictureNotFoundException;
import lt.codeacademy.blog.repository.PictureRepository;
import org.springframework.stereotype.Service;

@Service
public class PictureService {

    private final PictureRepository pictureRepository;

    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public Picture getPicture(Long id){
        return pictureRepository.findById(id)
                .map(Picture::convert)
                .orElseThrow(() -> new PictureNotFoundException(id));
    }
}
