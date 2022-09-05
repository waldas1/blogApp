package lt.codeacademy.blog.controller;

import lt.codeacademy.blog.dto.Comment;
import lt.codeacademy.blog.dto.Content;
import lt.codeacademy.blog.dto.User;
import lt.codeacademy.blog.service.CommentService;
import lt.codeacademy.blog.service.ContentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.time.LocalDate;
import java.util.UUID;

@Controller
@RequestMapping()
public class CommentController {

    private final CommentService commentService;
    private final ContentService contentService;

    public CommentController(CommentService commentService, ContentService contentService) {
        this.commentService = commentService;
        this.contentService = contentService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("comments/{id}/save")
    public String newComment(@PathVariable UUID id, Model model) {
        model.addAttribute("comment", new Comment());
        model.addAttribute("content", contentService.getContent(id));
        return "form/createComment";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("comments/{id}/save")
    public String createComment(@Valid Comment comment, @PathVariable UUID id, @AuthenticationPrincipal User user) {

        Content content = contentService.getContent(id);
        comment.setContent(content);
        comment.setDate(LocalDate.now());
        comment.setUser(user);
        commentService.createNewComment(comment);

        return "redirect:/public/content/{id}";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("update/{id}")
    public String updateComment(@PathVariable UUID id, @Valid Comment comment) {
        comment.setUser(commentService.getComment(id).getUser());
        comment.setContent(commentService.getComment(id).getContent());
        commentService.updateComment(comment);
        return "redirect:/public/content" + commentService.getComment(id).getContent().getId().toString();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("update/{id}")
    public String updateForm(@PathVariable UUID id, Model model) {
        model.addAttribute("comment", commentService.getComment(id));
        return "form/createComment";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String deleteComment(@PathVariable UUID id) {
        UUID contentId = commentService.getComment(id).getContent().getId();
        commentService.deleteComment(id);
        return "redirect:/public/content/";
    }
}
