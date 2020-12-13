package pl.gregorymartin.touristapp.trip;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository {
    List<Booking> findAllByAppUser_IdOrderByIdDesc(long userId);

    List<Booking> findAllByUserIdAndSortByDepartureDateFuture(long userId, ZonedDateTime now);

    List<Booking> findAllByUserIdAndSortByDepartureDatePast(long userId, ZonedDateTime now);

    Page<Booking> findAll(Pageable pageable);

    Optional<Booking> findById(long id);

    Booking save(Booking booking);

    void delete(Booking booking);
}
