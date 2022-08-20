package lt.codeacademy.blog.controller;

import lombok.extern.slf4j.Slf4j;
import lt.codeacademy.blog.service.PictureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class PictureController {

    private final PictureService pictureService;

    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("public/content/{id}")
    public String showSelectedPicture(@PathVariable Long id, Model model){
        model.addAttribute("picture", pictureService.getPicture(id));
        return "contentDetails";
    }
}
