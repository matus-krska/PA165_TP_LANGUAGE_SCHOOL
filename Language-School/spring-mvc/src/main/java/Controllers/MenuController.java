package Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Menu controller
 *
 * @author Simon Hyben, 421112
 *
 */
@Controller
public class MenuController {

    @RequestMapping(value = "/home")
    public String index() {
        return "home";
    }
}