package wad.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Askare;
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
    
    @RequestMapping(value = "/askareet", method = RequestMethod.POST)
    public String lisaaAskare(@RequestParam String nimi, 
            @RequestParam String luokka) {
        Askare a = new Askare();
        a.setNimi(nimi);
        askareRepository.save(a);

        return "redirect:/askareet";
    }    
}
