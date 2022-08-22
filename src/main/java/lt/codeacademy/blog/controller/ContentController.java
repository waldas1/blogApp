package lt.codeacademy.blog.controller;

import lt.codeacademy.blog.dto.Content;
import lt.codeacademy.blog.service.ContentService;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.Size;
import java.util.UUID;

@Controller
public class ContentController {

    private final ContentService contentService;

    public ContentController(ContentService pictureService) {
        this.contentService = pictureService;
    }

    @GetMapping("/public/content")
    public String showAllContent(Model model, Pageable pageable) {
        Page<Content> page = contentService.getContents(pageable);
        model.addAttribute("contentByPage", page);

        return "content";
    }

    @GetMapping("/public/content/{id}")
    public String openPictureDetails(@PathVariable UUID id, @Size(max = 6) Model model) {
        model.addAttribute("content", contentService.getContent(id));
        return "contentDetails";
    }

    /*@GetMapping("/public/content")
    public String pictureComments(Model model, @Size(max = 3) String picUrl) {
        model.addAttribute("comments", contentService.getCommentsByPicUrl(picUrl));
        return "content";
    }*/

}
