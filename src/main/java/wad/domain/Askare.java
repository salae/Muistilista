package wad.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author Anu
 */
@Entity
public class Askare extends AbstractPersistable<Long> {
    
    @NotBlank
    private String nimi;
    private int tarkeys = 1;    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Kayttaja omistaja;
    @ManyToMany(mappedBy = "askareet", fetch = FetchType.EAGER)
    private List<Luokka> luokat;

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public int getTarkeys() {
        return tarkeys;
    }

    public void setTarkeys(int tarkeys) {
        this.tarkeys = tarkeys;
    }

    public Kayttaja getOmistaja() {
        return omistaja;
    }

    public void setOmistaja(Kayttaja omistaja) {
        this.omistaja = omistaja;
    }

    public List<Luokka> getLuokat() {
         if (this.luokat == null) {
            this.luokat = new ArrayList<>();
        }         
        return luokat;
    }

    public void setLuokka(Luokka luokka) {
          if (this.luokat == null) {
            this.luokat = new ArrayList<>();
        }         
        this.luokat.add(luokka);
    }
  
}
