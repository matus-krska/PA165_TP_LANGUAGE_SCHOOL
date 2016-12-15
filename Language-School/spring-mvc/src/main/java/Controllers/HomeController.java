package Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home controller
 *
 * @author Simon Hyben, 421112
 *
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/errorpage")
    public String errorpage() {
        return "errorpage";
    }
}
