package pjatk.s24271.jaz301;

import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
