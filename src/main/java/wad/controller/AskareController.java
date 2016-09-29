package wad.controller;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Askare;
import wad.domain.Kayttaja;
import wad.domain.Luokka;
import wad.repository.AskareRepository;
import wad.repository.LuokkaRepository;
import wad.service.AskareService;
import wad.service.KayttajaService;
import wad.service.LuokkaService;

/**
 *
 * @author Anu
 */
@Controller
public class AskareController {
    
    @Autowired
    private AskareRepository askareRepository;
//    @Autowired
//    private LuokkaRepository luokkaRepository;
    @Autowired
    private AskareService askareService;
    @Autowired
    private LuokkaService luokkaService;
    @Autowired
    private KayttajaService kayttajaService; 
    
    @PostConstruct
    public void init() {
        Kayttaja k = new Kayttaja();
        k.setNimi("nalle");
        k.setSalasana("puupuu");
        kayttajaService.lisaa(k);
        Askare a = new Askare();
        a.setNimi("tiskaa");
        a.setOmistaja(k);
        a.setTarkeys(3);
        askareRepository.save(a);
        Askare b = new Askare();
        b.setNimi("lue artikkeli");
        b.setOmistaja(k);
        b.setTarkeys(4);
        askareRepository.save(b);
    }

    @RequestMapping(value = "/askareet", method = RequestMethod.GET)
    public String listaaAskareet(Model model) {
        model.addAttribute("askareet", askareService.listaaKaikki());
        return "askareet";
    }
    
//    @RequestMapping(value = "/askareet", method = RequestMethod.POST)
//    public String lisaaAskare(@RequestParam String nimi, 
//            @RequestParam String luokka) {
//        askareService.lisaa(nimi, luokka);
//        return "redirect:/askareet";
//    }
    
    @RequestMapping(value = "/askareet", method = RequestMethod.POST)
    public String lisaaAskare(@RequestParam String nimi) {
        askareService.lisaa(nimi);
        return "redirect:/askareet";
    }    
    
    @RequestMapping(value = "/askareet/{id}", method = RequestMethod.GET)
    public String naytaAskare(Model model, @PathVariable Long id) {
        model.addAttribute("askare", askareService.hae(id));
        model.addAttribute("askareet", askareService.listaaKaikki());
        model.addAttribute("luokat", luokkaService.listaaLuokatIlmanAskaretta(id));
//        model.addAttribute("kayttaja", kayttajaService.palautaKirjautuja());
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
        return "redirect:/askareet";
    }    
    
}
