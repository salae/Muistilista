package wad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Anu
 */
@Controller
public class DefaultController {
    
    @RequestMapping("*")
    public String aloitus(Model model) {
        return "index";
    }  
    
   
}

