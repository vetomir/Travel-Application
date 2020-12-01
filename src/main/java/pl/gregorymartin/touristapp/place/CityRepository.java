package pl.gregorymartin.touristapp.place;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CityRepository {
    Optional<City> findById(long id);

    Page<City> findAll(Pageable pageable);

    City save(City city);

    void delete(City city);

    Optional<City> findByName(String departureCityName);
}
