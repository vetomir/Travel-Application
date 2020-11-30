package pl.gregorymartin.touristapp.place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlCityRepository extends JpaRepository<City, Long> {
}
