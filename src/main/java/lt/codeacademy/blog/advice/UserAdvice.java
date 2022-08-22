package lt.codeacademy.blog.advice;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.beans.PropertyEditor;
import java.util.Date;

@ControllerAdvice
public class UserAdvice {
    @InitBinder
    public void initStringBinder(WebDataBinder webDataBinder) {
        PropertyEditor editor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, editor);
    }

    @ModelAttribute("myDate")
    public Date getCurrentDate() {
        return new Date();
    }
}
