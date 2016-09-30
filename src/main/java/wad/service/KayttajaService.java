package wad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wad.domain.Askare;
import wad.domain.Kayttaja;
import wad.domain.Luokka;
import wad.repository.AskareRepository;
import wad.repository.KayttajaRepository;
import wad.repository.LuokkaRepository;

/**
 *
 * @author Anu
 */
@Service
public class KayttajaService {
    @Autowired
    private AskareRepository askareRepository;
    @Autowired
    private LuokkaRepository luokkaRepository;
    @Autowired
    private KayttajaRepository kayttajaRepository;
    @Autowired
    private AskareService askareService;
    @Autowired
    private LuokkaService luokkaService;    

    public Iterable<Kayttaja> listaaKaikki() {
        return kayttajaRepository.findAll();
    }
    
    @Transactional
    public void lisaa(Kayttaja kayttaja) {
        kayttajaRepository.saveAndFlush(kayttaja);
    }
    
    public Kayttaja palautaKirjautuja() {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return kayttajaRepository.findByNimi(auth.getName());
    }
    
    @Transactional
    public void poista(Long id) {
        Kayttaja kayttaja = kayttajaRepository.findOne(id);
        for (Luokka l : kayttaja.getLuokat()) {
            luokkaService.poista(l.getId());
        }       
        for (Askare a : kayttaja.getAskareet()) {
            askareService.poista(a.getId());
        }
 
       kayttajaRepository.delete(kayttaja);
    }    
}
