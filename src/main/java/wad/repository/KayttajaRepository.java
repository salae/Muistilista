package wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Kayttaja;

/**
 *
 * @author Anu
 */
public interface KayttajaRepository extends JpaRepository<Kayttaja, Long>{
    Kayttaja findByNimi(String nimi);
}
