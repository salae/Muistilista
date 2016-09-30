package wad.repository;

import java.util.List;
//import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Askare;

/**
 *
 * @author Anu
 */
public interface AskareRepository extends JpaRepository<Askare, Long>{
    
    Askare findByNimi(String nimi);
    List<Askare> findAllByOrderByTarkeysAsc();
}
