package wad.controller;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class LuokkaController {

    @Autowired
    private LuokkaRepository luokkaRepository;
    @Autowired
    private AskareService askareService;
    @Autowired
    private LuokkaService luokkaService;    

//    @PostConstruct
//    public void init() {
//        luokkaService.lisaa("koti");
//        luokkaService.lisaa("opiskelu");
//        luokkaService.lisaa("vapaa-aika");
//    }  
    
    @ModelAttribute
    private Luokka getLuokka(){
        return new Luokka();
    }    
    
    @RequestMapping(value = "/luokat", method = RequestMethod.GET)
    public String listaaLuokat(Model model) {
        model.addAttribute("luokat", luokkaService.listaaKaikki());
        return "luokat";
    }
  
    @RequestMapping(value = "/luokat", method = RequestMethod.POST)
    public String lisaaLuokka(@Valid @ModelAttribute Luokka luokka, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "luokat";
        }    
        luokkaService.lisaa(luokka);
        return "redirect:/luokat";
    } 

    @RequestMapping(value = "/luokat/{id}", method = RequestMethod.GET)
    public String naytaLuokka(Model model, @PathVariable Long id) {
        model.addAttribute("luokka",luokkaService.hae(id));
        return "luokka";
    }    
    
    @RequestMapping(value = "/luokat/{id}", method = RequestMethod.DELETE)
    public String poistaLuokka(@PathVariable long id) {
        luokkaService.poista(id);
        return "redirect:/luokat";
    }
   
}
