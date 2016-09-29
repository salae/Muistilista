package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
public class AskareController {
    
    @Autowired
    private AskareRepository askareRepository;
    @Autowired
    private LuokkaRepository luokkaRepository;
    @Autowired
    private AskareService askareService;
    @Autowired
    private LuokkaService luokkaService;

    @RequestMapping(value = "/askareet", method = RequestMethod.GET)
    public String listaaAskareet(Model model) {
        model.addAttribute("askareet", askareService.listaaKaikki());
        return "askareet";
    }
    
    @RequestMapping(value = "/askareet", method = RequestMethod.POST)
    public String lisaaAskare(@RequestParam String nimi, 
            @RequestParam String luokka) {
        askareService.lisaa(nimi, luokka);
        return "redirect:/askareet";
    }
    
    @RequestMapping(value = "/askareet/{id}", method = RequestMethod.GET)
    public String naytaAskare(Model model, @PathVariable Long id) {
        model.addAttribute("askare", askareRepository.findOne(id));
        model.addAttribute("askareet", askareRepository.findAll());
        model.addAttribute("luokat", luokkaRepository.findAll());
        return "askare";
    }
    
    @RequestMapping(value = "/askareet/{id}", method = RequestMethod.DELETE)
    public String poistaAskare(@PathVariable Long id) {
        askareRepository.delete(askareRepository.findOne(id));
        return "redirect:/askareet";
    }     
}
