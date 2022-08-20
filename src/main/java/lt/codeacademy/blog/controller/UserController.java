package lt.codeacademy.blog.controller;

import lt.codeacademy.blog.dto.User;
import lt.codeacademy.blog.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blogoo")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registerUser(Model model, User user) {
        model.addAttribute("user", new User());
        return "form/registration";
    }

    @PostMapping("/registration")
    public String createProduct(User user, Model model) {
        model.addAttribute("user", new User());
        userService.createUser(user);
        return "redirect:/blogoo/users";
    }

    @GetMapping("/users")
    public String showUsers(Model model, Pageable pageable) {
        model.addAttribute("usersInPage", userService.getUsers(pageable));
        return "users";
    }

    @GetMapping
    public String start() {
        return "login";
    }

//    @GetMapping("{role}")
//    public String start(Model model, @RequestParam(required = true) Role role){
//        if (role == role.ADMIN){
//
//        }else if (role == role.USER){
//
//        }else if(role == role.GUEST){
//
//        }
//        return ;
//    }
}
