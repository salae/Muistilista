package wad.service;

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
    
    public Iterable<Askare> listaaKaikki() {
        return askareRepository.findAll();
    }    
    
//    @Transactional
//    public void lisaa(String nimi, String luokka) {
//        Askare a = new Askare();
//        a.setNimi(nimi);
////        if(luokka != null)
////        Luokka l = new Luokka();
////        l.setNimi(luokka);
////        a.setLuokka(l);
////        l.setAskare(a);
//        askareRepository.save(a);
////        luokkaRepository.save(l);
//         
//    }

    @Transactional
    public void lisaa(String nimi) {
        Askare a = new Askare();
        a.setNimi(nimi);
        a.setOmistaja(kayttajaService.palautaKirjautuja());
        askareRepository.save(a);        
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
   
}
