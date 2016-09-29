package wad.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author Anu
 */
@Entity
public class Kayttaja extends AbstractPersistable<Long>{
    
    @NotBlank
    @Length(min = 3, max = 20)
    private String nimi;    //käyttäjätunnus ja nimi samassa
    @NotBlank
    @Length(min = 5, max = 20)
    private String salasana;
    @OneToMany(mappedBy="omistaja")
    private List<Askare> askareet;
    @OneToMany(mappedBy="omistaja")
    private List<Luokka> luokat;

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getSalasana() {
        return salasana;
    }

    public void setSalasana(String salasana) {
        this.salasana = salasana;
    }

    public List<Askare> getAskareet() {
        return askareet;
    }

    public void setAskareet(List<Askare> askareet) {
        this.askareet = askareet;
    }

    public List<Luokka> getLuokat() {
        return luokat;
    }

    public void setLuokat(List<Luokka> luokat) {
        this.luokat = luokat;
    }
    
    
    
}
