package pl.gregorymartin.touristapp.trip;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface OfferRepository {
    Optional<Offer> findById(long offerId);

    List<Offer> searchOffers(String from, String to, ZonedDateTime when);

    List<Offer> searchOffersWithoutTo(String from, ZonedDateTime when);

    List<Offer> searchOffersWithoutFrom(String to, ZonedDateTime when);

    List<String> availableCitiesToDeparture();

    List<String> availableCitiesToArrive();

    Page<Offer> findAllNotExpired(Pageable page);

    Page<Offer> findAll(Pageable page);

    Offer save(Offer offer);

    void delete(Offer offer);
}
