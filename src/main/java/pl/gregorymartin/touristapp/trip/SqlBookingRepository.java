package pl.gregorymartin.touristapp.trip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public
interface SqlBookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByAppUser_IdOrderByIdDesc(long userId);



    @Query("Select b From Booking b where b.appUser.id = ?1 and b.offer.departureTime > ?2 order by b.offer.departureTime desc ")
    List<Booking> findAllByUserIdAndSortByDepartureDateFuture(long userId, ZonedDateTime now);

    @Query("Select b From Booking b where b.appUser.id = ?1 and b.offer.departureTime < ?2 order by b.offer.departureTime desc ")
    List<Booking> findAllByUserIdAndSortByDepartureDatePast(long userId, ZonedDateTime now);
}
