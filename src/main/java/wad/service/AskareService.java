package wad.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Askare;
import wad.domain.Luokka;
import wad.repository.AskareRepository;
import wad.repository.LuokkaRepository;

/**
 *
 * @author Anu
 */
@Service
public class AskareService {
 
    @Autowired
    private AskareRepository askareRepository;
    @Autowired
    private LuokkaRepository luokkaRepository;
    @Autowired
    private KayttajaService kayttajaService;  
    
    public List<Askare> listaaKaikki() {
        return askareRepository.findAllByOrderByTarkeysAsc();        
    }    
    
    @Transactional
    public void lisaa(Askare askare) {
//        Askare a = new Askare();
//        a.setNimi(nimi);
//        a.setOmistaja(kayttajaService.palautaKirjautuja());
        askareRepository.save(askare);        
    }    
    
    @Transactional
    public void poista(Long id) {
        Askare askare = askareRepository.findOne(id);
        for (Luokka l : askare.getLuokat()) {
            l.getAskareet().remove(askare);
        }
 
       askareRepository.delete(askare);
    }
     
    @Transactional
    public void muutaPrioriteeti(Long id, int tarkeys){
        Askare askare = askareRepository.findOne(id);
        askare.setTarkeys(tarkeys);
        askareRepository.saveAndFlush(askare);
    }    
    
    @Transactional
    public void lisaaAskareLuokkaan(Long askareId, Long luokkaId) {
        Askare askare = askareRepository.findOne(askareId);
        Luokka luokka = luokkaRepository.findOne(luokkaId);
 
        askare.getLuokat().add(luokka);
        luokka.getAskareet().add(askare);
    }
 
    public Askare hae(Long id) {
        return askareRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public List<Askare> listaaTietynLuokanAskareet(Long luokkaId) {
        Luokka luokka = luokkaRepository.findOne(luokkaId);
        return askareRepository.etsiTietynLuokanAskareet(luokka);
    }    
}
