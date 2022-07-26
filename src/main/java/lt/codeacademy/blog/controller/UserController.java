package lt.codeacademy.blog.controller;

import lt.codeacademy.blog.dto.User;
import lt.codeacademy.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/save")
    public String registerUser(Model model) {
        model.addAttribute("user", new User());
        return "form/registration";
    }

    @PostMapping("/save")
    public String createProduct(User user,Model model) {
        model.addAttribute("user", new User());
        userService.createUser(user);
        return "users";
    }
}
