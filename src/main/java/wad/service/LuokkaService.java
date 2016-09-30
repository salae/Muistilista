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
public class LuokkaService {
    
    @Autowired
    private AskareRepository askareRepository;
    @Autowired
    private LuokkaRepository luokkaRepository;

    public Iterable<Luokka> listaaKaikki() {
        return luokkaRepository.findAll();
    }
    
    @Transactional
    public void lisaa(Luokka luokka) {
        luokkaRepository.save(luokka);
    }
    
    @Transactional
    public void poista(Long id) {
        Luokka luokka = luokkaRepository.findOne(id);
        for (Askare a : luokka.getAskareet()) {
            a.getLuokat().remove(luokka);
        }
 
       luokkaRepository.delete(luokka);
    }   
 
    @Transactional(readOnly = true)
    public Luokka hae(Long id) {
        Luokka luokka = luokkaRepository.findOne(id);
        return luokka;
    }
 
    @Transactional(readOnly = true)
    public Iterable<Luokka> listaaLuokatIlmanAskaretta(Long askareId) {
        Askare askare = askareRepository.findOne(askareId);
        return luokkaRepository.etsiLuokatIlmanAskaretta(askare);
    }
   
}
