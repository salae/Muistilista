package wad;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wad.domain.Askare;
import wad.domain.Kayttaja;
import wad.domain.Luokka;
import wad.repository.AskareRepository;
import wad.repository.KayttajaRepository;
import wad.repository.LuokkaRepository;
import wad.service.AskareService;
import wad.service.KayttajaService;
import wad.service.LuokkaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MuistilistaApplicationTests {
    
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
    @Autowired
    private KayttajaService kayttajaService;                 

    Askare askare;
    Luokka luokka;
    Kayttaja kayttaja;
    
    @Before
    public void setUp() { 
        
        kayttaja = new Kayttaja();
        kayttaja.setNimi("testaaja");
        kayttaja.setSalasana("salasana");
        
        askare = new Askare();
        askare.setNimi("pese ikkunat");
        askare.setOmistaja(kayttaja);
        
        luokka = new Luokka();
        luokka.setNimi("koti"); 
        luokka.setOmistaja(kayttaja);

    }
    
    @Test
    public void contextLoads() {
    }
    
    @Test
    public void testAskare() {

        kayttajaService.lisaa(kayttaja);
        
        askareService.lisaa(askare);     
        Askare haettu = askareRepository.findByNimi("pese ikkunat");
        assertNotNull(haettu);
        assertEquals(1, haettu.getTarkeys());
        assertEquals(kayttaja, haettu.getOmistaja());       

    }
    
    @Test
    public void testAskarePrioriteetinMuutos(){
        
        Askare a = new Askare();
        a.setNimi("juo");
        askareService.lisaa(a);
//        Askare hae = askareRepository.findByNimi("juo");
        askareService.muutaPrioriteeti(a.getId(), 5);
         Askare hae = askareRepository.findByNimi("juo");
        assertEquals(5, hae.getTarkeys());        
        
    }
     
    @Test
    public void testLuokka() {        

        kayttajaService.lisaa(kayttaja);
        luokkaService.lisaa(luokka);
                
        Luokka haettu = luokkaRepository.findByNimi("koti");
        assertNotNull(haettu);
        assertEquals(kayttaja, haettu.getOmistaja());

    }
    
    @Test
    public void testKayttaja() {
        Kayttaja k = new Kayttaja();
        k.setNimi("tytti");
        k.setSalasana("tuutti");
        
        kayttajaService.lisaa(k);
        
        Kayttaja haettu = kayttajaRepository.findByNimi("tytti");
        assertNotNull(haettu);
       
    }
}
