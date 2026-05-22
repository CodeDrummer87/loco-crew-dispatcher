package x.team.loco_crew_dispather.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin")
    public String admin() {
        return "forward:/html/admin.html";
    }
}
