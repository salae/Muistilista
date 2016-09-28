package wad.domain;

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
public class Luokka  extends AbstractPersistable<Long>{
    
//    @NotBlank
    private String nimi;
    @ManyToOne
    @JoinColumn
    private Kayttaja omistaja;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Askare> askareet;
    
    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public Kayttaja getOmistaja() {
        return omistaja;
    }

    public void setOmistaja(Kayttaja omistaja) {
        this.omistaja = omistaja;
    }

    public List<Askare> getAskareet() {
        return askareet;
    }

    public void setAskareet(List<Askare> askareet) {
        this.askareet = askareet;
    }
    
    
}
