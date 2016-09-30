package wad.controller;

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
import wad.service.AskareService;
import wad.service.LuokkaService;

/**
 *
 * @author Anu
 */
@Controller
public class AskareController {
    
    @Autowired
    private AskareService askareService;
    @Autowired
    private LuokkaService luokkaService;
    
    @ModelAttribute
    private Askare getAskare(){
        return new Askare();
    }
    
    @RequestMapping(value = "/askareet", method = RequestMethod.GET)
    public String listaaAskareet(Model model) {
        model.addAttribute("askareet", askareService.listaaKaikki());
        return "askareet";
    }
    
    @RequestMapping(value = "/askareet", method = RequestMethod.POST)
    public String lisaaAskare(@Valid @ModelAttribute Askare askare, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "askareet";
        }
        askareService.lisaa(askare);
        return "redirect:/askareet";
    }    
    
    @RequestMapping(value = "/askareet/{id}", method = RequestMethod.GET)
    public String naytaAskare(Model model, @PathVariable Long id) {
        model.addAttribute("askare", askareService.hae(id));
        model.addAttribute("askareet", askareService.listaaKaikki());
        model.addAttribute("luokat", luokkaService.listaaLuokatIlmanAskaretta(id));
        return "askare";
    }
    
    @RequestMapping(value = "/askareet/{id}", method = RequestMethod.DELETE)
    public String poistaAskare(@PathVariable Long id) {
        askareService.poista(id);
        return "redirect:/askareet";
    } 
    
    @RequestMapping(value = "/askareet/{askareId}/yhdista", method = RequestMethod.POST)
    public String lisaaAskareelleLuokka(@PathVariable Long askareId, 
                    @RequestParam Long luokkaId) {
        askareService.lisaaAskareLuokkaan(askareId, luokkaId);
        return "redirect:/askareet/{askareId}";
    }
    
    @RequestMapping(value = "/askareet/{askareId}/tarkeysmuutos", method = RequestMethod.POST)
    public String muutaAskareenTarkeytta(@PathVariable Long askareId, 
                    @RequestParam Integer tarkeys) {
        askareService.muutaPrioriteeti(askareId, tarkeys);
        return "redirect:/askareet/{askareId}";
    }    
    
}
