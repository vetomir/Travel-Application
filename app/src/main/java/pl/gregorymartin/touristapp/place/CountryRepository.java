package pl.gregorymartin.touristapp.place;

import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.Optional;

public interface CountryRepository {
    Page<Country> findAll(Pageable pageable);

    Optional<Country> findById(long id);

    Optional<Country> findByName(String name);

    Country save(Country country);

    void deleteById(long id);
}
