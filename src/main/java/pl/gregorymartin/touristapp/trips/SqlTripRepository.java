package pl.gregorymartin.touristapp.trips;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlTripRepository extends JpaRepository<Trip, Long> {
}
