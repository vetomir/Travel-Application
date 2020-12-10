package pl.gregorymartin.touristapp.trip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Repository
interface SqlOfferRepository extends JpaRepository<Offer, Long> {

    @Query("Select o From Offer o where o.departureCityName = ?1 AND o.arrivingCityName = ?2 AND o.departureTime >= ?3")
    List<Offer> searchOffers(String from, String to, ZonedDateTime when);

    @Query("Select o From Offer o where o.departureCityName = ?1 AND o.departureTime >= ?2")
    List<Offer> searchOffersWithoutTo(String from, ZonedDateTime when);

    @Query("Select o From Offer o where o.arrivingCityName = ?1 AND o.departureTime >= ?2")
    List<Offer> searchOffersWithoutFrom(String to, ZonedDateTime when);
}
