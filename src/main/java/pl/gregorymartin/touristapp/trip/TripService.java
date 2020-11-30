package pl.gregorymartin.touristapp.trip;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gregorymartin.touristapp.trip.dto.BookingReadModel;
import pl.gregorymartin.touristapp.trip.dto.BookingWriteModel;
import pl.gregorymartin.touristapp.trip.dto.OfferReadModel;
import pl.gregorymartin.touristapp.trip.dto.OfferWriteModel;

import java.util.List;
import java.util.Optional;

@Service
class TripService {
    private SqlBookingRepository bookingRepository;
    private SqlOfferRepository offerRepository;

    TripService(final SqlBookingRepository bookingRepository, final SqlOfferRepository offerRepository) {
        this.bookingRepository = bookingRepository;
        this.offerRepository = offerRepository;
    }

    List<OfferReadModel> getAllOffers(int page, Sort.Direction sort, String sortBy, int items){
        List<Offer> offers = offerRepository.findAll(
                PageRequest.of(page, items,
                        Sort.by(sort, sortBy)
                )).getContent();
        return OfferFactory.toDto(offers);
    }

    List<BookingReadModel> getAllBookings(int page, Sort.Direction sort, String sortBy, int items){
        List<Booking> offers = bookingRepository.findAll(
                PageRequest.of(page, items,
                        Sort.by(sort, sortBy)
                )).getContent();
        return BookingFactory.toDto(offers);
    }

    OfferReadModel getOfferById(long id){
        Optional<Offer> offer = offerRepository.findById(id);
        if (offer.isEmpty()){
            throw new IllegalArgumentException("Offer is not presents");
        }

        return OfferFactory.toDto(offer.get());
    }

    BookingReadModel getBookingById(long id){
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isEmpty()){
            throw new IllegalArgumentException("Booking is not presents");
        }
        return BookingFactory.toDto(booking.get());
    }

    OfferReadModel addOffer(OfferWriteModel offerWriteModel){
        Offer offer = OfferFactory.toEntity(offerWriteModel);

        return OfferFactory.toDto(offerRepository.save(offer));
    }

    BookingReadModel addBooking(BookingWriteModel bookingWriteModel){
        Booking booking = BookingFactory.toEntity(bookingWriteModel);

        return BookingFactory.toDto(bookingRepository.save(booking));
    }

    @Transactional
    OfferReadModel modifyOffer(long id, OfferWriteModel offerWriteModel){
        Optional<Offer> offerById = offerRepository.findById(id);
        if(offerById.isPresent()){
            Offer offer= OfferFactory.toEntity(offerWriteModel);
            offer.setId(id);

            return OfferFactory.toDto(offerRepository.save(offer));
        }
        throw new IllegalArgumentException("Offer with is not presents");
    }

    @Transactional
    BookingReadModel modifyBooking(long id, BookingWriteModel bookingWriteModel){
        Optional<Booking> bookingById = bookingRepository.findById(id);
        if(bookingById.isPresent()){
            Booking booking= BookingFactory.toEntity(bookingWriteModel);
            booking.setId(id);

            return BookingFactory.toDto(bookingRepository.save(booking));
        }
        throw new IllegalArgumentException("City with is not presents");
    }

    boolean deleteOffer(long id){
        Optional<Offer> offerById = offerRepository.findById(id);
        if(offerById.isPresent()){
            offerRepository.delete(offerById.get());

            return true;
        }
        throw new IllegalArgumentException("Offer with this id is not presents");
    }


    boolean deleteBooking(long id){
        Optional<Booking> bookingById = bookingRepository.findById(id);
        if(bookingById.isPresent()){
            bookingRepository.delete(bookingById.get());

            return true;
        }
        throw new IllegalArgumentException("Booking with this id is not presents");
    }

}
