package wad.Config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import wad.domain.Kayttaja;
import wad.repository.KayttajaRepository;


@Component
public class JpaAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private KayttajaRepository kayttajaRepositio;

    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        String nimi = a.getPrincipal().toString();
        String salasana = a.getCredentials().toString();
        Kayttaja kayttaja = kayttajaRepositio.findByNimi(nimi);

        if (kayttaja == null) {
            throw new AuthenticationException("Ei voida autentikoida kayttajaa " + nimi) {
            };
        }
//        if (!BCrypt..hashpw(salasana, kayttaja.getSalt()).equals(kayttaja.getPassword())) {
//            throw new AuthenticationException("Unable to authenticate user " + nimi) {
//            };
//        }

        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority("USER"));

        return new UsernamePasswordAuthenticationToken(kayttaja.getNimi(), salasana, grantedAuths);
    }

    @Override
    public boolean supports(Class<?> type) {
        return true;
    }
}