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

    public Iterable<Askare> listaaKaikki() {
        return askareRepository.findAll();
    }    
    
    @Transactional
    public void lisaa(String nimi, String luokka) {
        Askare a = new Askare();
        a.setNimi(nimi);
//        if(luokka != null)
//        Luokka l = new Luokka();
//        l.setNimi(luokka);
//        a.setLuokka(l);
//        l.setAskare(a);
        askareRepository.save(a);
//        luokkaRepository.save(l);
         
    }

    @Transactional
    void poista(Long id) {
        Askare askare = askareRepository.findOne(id);
        for (Luokka l : askare.getLuokat()) {
            l.getAskareet().remove(askare);
        }
 
       askareRepository.delete(askare);    }
    
    
}
