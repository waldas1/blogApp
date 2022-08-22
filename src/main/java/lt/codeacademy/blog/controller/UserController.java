package lt.codeacademy.blog.controller;

import lombok.extern.slf4j.Slf4j;
import lt.codeacademy.blog.dto.User;
import lt.codeacademy.blog.service.UserService;
import lt.codeacademy.blog.validator.UserValidator;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/public/blogoo")
public class UserController {
    private final UserService userService;
    private final UserValidator userValidator;

    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("/registration")
    public String registerUser(Model model) {
        model.addAttribute("user", new User());
        return "form/registration";
    }

    @PostMapping("/registration")
    public String createUser(@Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "form/login";
        }
        userService.createUser(user);
        return "redirect:/public/blogoo/registration";
    }

}
