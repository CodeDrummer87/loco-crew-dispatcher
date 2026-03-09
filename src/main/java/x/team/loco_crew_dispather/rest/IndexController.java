package x.team.loco_crew_dispather.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/index.html")
    public String index() {
        //Возвращаем путь к HTML-файлу внутри папки static
        return "forward:/html/index.html";
    }
}
