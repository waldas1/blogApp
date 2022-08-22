package lt.codeacademy.blog.controller;

import lombok.extern.slf4j.Slf4j;
import lt.codeacademy.blog.dto.Content;
import lt.codeacademy.blog.service.ContentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.time.LocalDate;
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
    public String openContentDetails(@PathVariable UUID id, @Size(max = 6) Model model) {
        model.addAttribute("content", contentService.getContent(id));
        return "contentDetails";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/content/{id}/update")
    public String showUpdateContent(@PathVariable UUID id, Model model) {
        model.addAttribute("content", contentService.getContent(id));
        return "addNewContent";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/content/{id}/update")
    public String updateContent(@Valid Content content) {
        contentService.updateContent(content);
        return "redirect:/public/content";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/content/{id}/delete")
    public String deleteContent(@PathVariable UUID id) {
        contentService.deleteContent(id);
        return "redirect:/public/content";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/addNewContent/save")
    public String showCreateContentForm(Model model){
        model.addAttribute("addNewContent", new Content());
        return "form/addNewContent";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/addNewContent/save")
    public String createContent(@Valid Content content, RedirectAttributes redirectAttributes){
        contentService.createContent(content);
        content.setDate(LocalDate.now());
        redirectAttributes.addFlashAttribute("message", "lt.codeacademy.blog.content.create.message.success");
        return "redirect:/addNewContent/save";

    }

}
