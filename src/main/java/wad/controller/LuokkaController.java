package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Askare;
import wad.domain.Luokka;
import wad.repository.AskareRepository;
import wad.repository.LuokkaRepository;

/**
 *
 * @author Anu
 */
public class LuokkaController {

    @Autowired
    private LuokkaRepository luokkaRepository;

    @RequestMapping(value = "/luokat", method = RequestMethod.GET)
    public String listaaAskareet(Model model) {
        model.addAttribute("luokat", luokkaRepository.findAll());
        return "luokat";
    }
    
    @RequestMapping(value = "/luokat", method = RequestMethod.POST)
    public String lisaaLuokka(@RequestParam String nimi) {
        Luokka l = new Luokka();
        l.setNimi(nimi);
        luokkaRepository.save(l);

        return "redirect:/luokat";
    }     
}
