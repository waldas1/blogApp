package lt.codeacademy.blog.config;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Setter
@Getter
@Configuration
@PropertySource("classpath:blog.properties")
public class Blog {
    @Value("Blogoo App")
    private String name;
    @Value("+3706269719")
    private String phoneNumber;
    @Value("waldas304@gmail.com")
    private String email;
    @Value("@All rights belong to Valdemar")
    private String copyRight;
    @Value("#{'Salcininkai, Mokyklos g. 21 Lithuania'.split(';')}")
    private List<String> addresses;
}
