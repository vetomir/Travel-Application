package pl.gregorymartin.touristapp.trip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public
interface SqlBookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByAppUser_IdOrderByIdDesc(long id);
}
