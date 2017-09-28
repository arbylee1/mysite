package mysite.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class ContactController {

    @RequestMapping("/contact")
    public String contact() {
        return "contact";
    }
}
