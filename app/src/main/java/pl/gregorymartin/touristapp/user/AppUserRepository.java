package pl.gregorymartin.touristapp.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AppUserRepository {
    AppUser findAllByUsername(String username);

    Optional<AppUser> findById(long l);

    Page<AppUser> findAll(Pageable pageable);

    AppUser save(AppUser result);

    void deleteById(long id);
}
