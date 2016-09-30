package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wad.domain.Askare;
import wad.domain.Luokka;

/**
 *
 * @author Anu
 */
public interface AskareRepository extends JpaRepository<Askare, Long>{
    
    Askare findByNimi(String nimi);
    List<Askare> findAllByOrderByTarkeysAsc();
    
    @Query("SELECT a FROM Askare a WHERE :luokka MEMBER OF a.luokat ORDER BY a.tarkeys")
    List<Askare> etsiTietynLuokanAskareet(@Param("luokka") Luokka luokka);    
}
