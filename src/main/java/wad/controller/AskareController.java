package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.repository.AskareRepository;
import wad.repository.LuokkaRepository;

/**
 *
 * @author Anu
 */
@Controller
public class AskareController {
    
    @Autowired
    private AskareRepository askareRepository;
    @Autowired
    private LuokkaRepository luokkaRepository;

    @RequestMapping(value = "/askareet", method = RequestMethod.GET)
    public String listaaAskareet(Model model) {
        model.addAttribute("askareet", askareRepository.findAll());
        return "askareet";
    }    
}
