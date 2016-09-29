package wad.controller;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
//import wad.service.KayttajaService;

/**
 *
 * @author Anu
 */
@Controller
public class DefaultController {   
   
    @RequestMapping("*")
    public String aloitus() {
        return "index";
    }  
    
   
}

