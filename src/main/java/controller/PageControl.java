package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageControl {


    @GetMapping("/about")
    public String about() {
        return "about";
    }  
   
}

