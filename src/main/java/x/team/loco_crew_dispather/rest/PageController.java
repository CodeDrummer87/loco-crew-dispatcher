package x.team.loco_crew_dispather.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/admin")
    public String admin() {
        return "forward:/html/admin.html";
    }

    @GetMapping("/user")
    public String user() {
        return "forward:/html/user.html";
    }

    @GetMapping("/login")
    public String login() {
        return "forward:/html/login.html";
    }
}
