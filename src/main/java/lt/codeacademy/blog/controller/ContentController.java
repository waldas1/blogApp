package lt.codeacademy.blog.controller;

import lombok.extern.slf4j.Slf4j;
import lt.codeacademy.blog.dto.Content;
import lt.codeacademy.blog.service.ContentService;
import org.springframework.data.web.SortDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.UUID;

@Slf4j
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/content/{id}/update")
    public String showUpdateContent(@PathVariable UUID id, Model model) {
        model.addAttribute("content", contentService.getContent(id));
        return "form/addNewEntry";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/content/{id}/update")
    public String updateContent(@Valid Content content) {
        contentService.updateContent(content);
        return "redirect:/public/content";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/content/{id}/update")
    public String deleteContent(@PathVariable UUID id) {
        contentService.deleteContent(id);
        return "redirect:/public/content";
    }

}
