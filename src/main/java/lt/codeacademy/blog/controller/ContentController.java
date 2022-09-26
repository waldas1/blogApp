package lt.codeacademy.blog.controller;

import lt.codeacademy.blog.dto.Content;
import lt.codeacademy.blog.service.CommentService;
import lt.codeacademy.blog.service.ContentService;
import lt.codeacademy.blog.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping()
public class ContentController {

    private final ContentService contentService;
    private final CommentService commentService;

    public ContentController(ContentService contentService, CommentService commentService) {
        this.contentService = contentService;
        this.commentService = commentService;
    }

    @GetMapping("/public/content")
    public String showAllContent(Model model, Pageable pageable) {
        model.addAttribute("contentByPage", contentService.getContents(pageable));

        return "content";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/addNewContent/save")
    public String showCreateContentForm(Model model) {
        model.addAttribute("content", new Content());
        return "form/addNewContent";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/addNewContent/save")
    public String createContent(@Valid Content content, RedirectAttributes redirectAttributes) {
        contentService.createContent(content);
        redirectAttributes.addFlashAttribute("message", "lt.codeacademy.blog.content.create.message.success");
        return "redirect:/addNewContent/save";
    }

    @GetMapping("/public/content/{id}")
    public String openContentDetails(@PathVariable UUID id, Model model, Pageable pageable) {
        model.addAttribute("content", contentService.getContent(id));
        model.addAttribute("comments", commentService.getComments(pageable));
        return "contentDetails";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/content/{id}/update")
    public String showUpdateContent(@PathVariable UUID id, Model model) {
        model.addAttribute("content", contentService.getContent(id));
        return "form/addNewContent";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/content/{id}/update")
    public String updateContent(@Valid Content content) {
        contentService.updateContent(content);
        return "redirect:/public/content/{id}";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/content/{id}/delete")
    public String deleteContent(@PathVariable UUID id) {
        contentService.deleteContent(id);
        return "redirect:/public/content";
    }
}
