package au.com.autogeneral.AutoGeneralTestAPI.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerController {
    @RequestMapping("/")
    public String greeting() {
        return "redirect:/swagger-ui.html";
    }
}
