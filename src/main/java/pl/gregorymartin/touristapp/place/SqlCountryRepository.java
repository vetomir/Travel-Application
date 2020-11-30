package pl.gregorymartin.touristapp.place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface SqlCountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findByName(String name);
}
