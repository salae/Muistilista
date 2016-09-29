package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Askare;
import wad.domain.Luokka;
import wad.repository.AskareRepository;
import wad.repository.LuokkaRepository;
import wad.service.AskareService;
import wad.service.LuokkaService;

/**
 *
 * @author Anu
 */
@Controller
public class LuokkaController {

    @Autowired
    private LuokkaRepository luokkaRepository;
    @Autowired
    private AskareService askareService;
    @Autowired
    private LuokkaService luokkaService;    

    @RequestMapping(value = "/luokat", method = RequestMethod.GET)
    public String listaaLuokat(Model model) {
        model.addAttribute("luokat", luokkaService.listaaKaikki());
        return "luokat";
    }
  
    @RequestMapping(value = "/luokat", method = RequestMethod.POST)
    public String lisaaLuokka(@RequestParam String nimi) {
        luokkaService.lisaa(nimi);
        return "redirect:/luokat";
    }     
}
