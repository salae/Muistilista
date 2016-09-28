package wad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Anu
 */
@Controller
public class DefaultController {

    
    @RequestMapping("/")
    public String aloitussivu() {
        return "index";
    }    
}

