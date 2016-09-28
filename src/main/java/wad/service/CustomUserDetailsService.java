package wad.service;

import java.util.Arrays;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wad.domain.Kayttaja;
import wad.repository.KayttajaRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    private KayttajaRepository kayttajaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Kayttaja kayttaja = kayttajaRepository.findByNimi(username);
        if (kayttaja == null) {
            throw new UsernameNotFoundException("Ei löydy käyttäjää: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                kayttaja.getNimi(),
                kayttaja.getSalasana(),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority("USER")));
    }    
}
