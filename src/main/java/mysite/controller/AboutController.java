package mysite.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class AboutController {

    @RequestMapping("/about")
    public String about() {
        return "about";
    }
}
