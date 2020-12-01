package pl.gregorymartin.touristapp.trip;

import org.springframework.data.domain.Sort;
import pl.gregorymartin.touristapp.trip.dto.*;

import java.util.List;

public interface TripService {
    List<OfferReadModel> getAllOffers(int page, Sort.Direction sort, String sortBy, int items);

    List<OfferMvcHomepage> getTopOffers(int page, Sort.Direction sort, String sortBy, int items);

    List<BookingReadModel> getAllBookings(int page, Sort.Direction sort, String sortBy, int items);

    OfferReadModel getOfferById(long id);

    BookingReadModel getBookingById(long id);

    OfferReadModel addOffer(OfferWriteModel offerWriteModel);

    BookingReadModel addBooking(BookingWriteModel bookingWriteModel);

    OfferReadModel modifyOffer(long id, OfferWriteModel offerWriteModel);

    BookingReadModel modifyBooking(long id, BookingWriteModel bookingWriteModel);

    BookingReadModel setPaid(long id);

    void deleteOffer(long id);

    void deleteBooking(long id);
}
