package pl.gregorymartin.touristapp.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
class UserDetailsServiceImpl implements UserDetailsService {

    private AppUserRepository repository;

    public UserDetailsServiceImpl(final AppUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(final String s) throws UsernameNotFoundException {

        return repository.findAllByUsername(s);
    }
}
