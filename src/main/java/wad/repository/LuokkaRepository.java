package wad.repository;

import java.util.List;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wad.domain.Askare;
import wad.domain.Luokka;

/**
 *
 * @author Anu
 */
public interface LuokkaRepository extends JpaRepository<Luokka, Long>{
 
    Luokka findByNimi(String nimi);
    
    @Query("SELECT l FROM Luokka l WHERE :askare NOT MEMBER OF l.askareet")
    List<Luokka> etsiLuokatIlmanAskaretta(@Param("askare") Askare askare);

}
