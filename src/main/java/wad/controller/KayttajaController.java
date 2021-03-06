package wad.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.domain.Kayttaja;
import wad.service.KayttajaService;

/**
 *
 * @author Anu
 */
@Controller
public class KayttajaController {

    @Autowired
    private KayttajaService kayttajaService;
    
    @RequestMapping(value="/tilinluonti", method=RequestMethod.GET)
    public String nayta(@ModelAttribute Kayttaja kayttaja) {
        return "tilinluonti";
    }
    
    @RequestMapping(value="/tilinluonti", method=RequestMethod.POST)
    public String luo(@Valid @ModelAttribute Kayttaja kayttaja, 
                                BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "tilinluonti";
        }
        kayttajaService.lisaa(kayttaja);
        return "redirect: index";
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String kirjaudu(Authentication auth) {
        if(auth.isAuthenticated())
            return "redirect:/";
        else {
            return "login";
        }
    }    
    
    @RequestMapping(value = "/kayttajat", method = RequestMethod.GET)
    public String listaaKayttajat(Model model) {
        model.addAttribute("kayttajat", kayttajaService.listaaKaikki());
        return "kayttajat";
    }    
 
    @RequestMapping(value = "/kayttajat/{id}", method = RequestMethod.DELETE)
    public String poistaKayttaja(@PathVariable long id) {
        kayttajaService.poista(id);
        return "redirect:/kayttajat";
    }    
}
