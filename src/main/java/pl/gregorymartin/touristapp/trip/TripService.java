package pl.gregorymartin.touristapp.trip;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gregorymartin.touristapp.place.City;
import pl.gregorymartin.touristapp.place.CityRepository;
import pl.gregorymartin.touristapp.trip.dto.BookingReadModel;
import pl.gregorymartin.touristapp.trip.dto.BookingWriteModel;
import pl.gregorymartin.touristapp.trip.dto.OfferReadModel;
import pl.gregorymartin.touristapp.trip.dto.OfferWriteModel;
import pl.gregorymartin.touristapp.user.AppUser;
import pl.gregorymartin.touristapp.user.SqlAppUserRepository;

import java.util.List;
import java.util.Optional;

@Service
class TripService {
    private SqlBookingRepository bookingRepository;
    private SqlOfferRepository offerRepository;
    private CityRepository cityRepository;
    private SqlAppUserRepository appUserRepository;

    TripService(final SqlBookingRepository bookingRepository, final SqlOfferRepository offerRepository, final CityRepository cityRepository, final SqlAppUserRepository appUserRepository) {
        this.bookingRepository = bookingRepository;
        this.offerRepository = offerRepository;
        this.cityRepository = cityRepository;
        this.appUserRepository = appUserRepository;
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
        if (offerWriteModel.getDepartureCityName().equals(offerWriteModel.getArrivingCityName())){
            throw new IllegalArgumentException("Cities cannot be the same");
        }
        Offer offer = OfferFactory.toEntity(offerWriteModel);
        Optional<City> departure = cityRepository.findByName(offerWriteModel.getDepartureCityName());
        Optional<City> arriving =  cityRepository.findByName(offerWriteModel.getArrivingCityName());
        if(departure.isEmpty() || arriving.isEmpty()){
            throw new IllegalArgumentException("City with this name is not presents");
        }
        offer.setDepartureCityName(departure.get().getName());
        offer.setArrivingCityName(arriving.get().getName());

        return OfferFactory.toDto(offerRepository.save(offer));
    }

    @Transactional
    BookingReadModel addBooking(BookingWriteModel bookingWriteModel){
        Booking booking = BookingFactory.toEntity(bookingWriteModel);
        Optional<AppUser> appUser = appUserRepository.findById(1L);
        Optional<Offer> offer = offerRepository.findById(bookingWriteModel.getOfferId());
        if(offer.isEmpty()){
            throw new IllegalArgumentException("Offer is not present!");
        }
        if (appUser.isEmpty()){
            throw new IllegalArgumentException("User is not present!");
        }
        booking.setAppUser(appUser.get());
        booking.setOffer(offer.get());
        booking.setPrice(offer.get().getPrice() * booking.getSize());

        offer.get().setCapacity(offer.get().getCapacity() - bookingWriteModel.getSize());
        offerRepository.save(offer.get());

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
        if(bookingById.isEmpty()){
            throw new IllegalArgumentException("Booking with is not presents");
        }
        Booking booking= BookingFactory.toEntity(bookingWriteModel);
        booking.setId(id);

        return BookingFactory.toDto(bookingRepository.save(booking));
    }

    @Transactional
    BookingReadModel setPaid(long id){
        Optional<Booking> bookingById = bookingRepository.findById(id);
        if(bookingById.isEmpty()){
            throw new IllegalArgumentException("Booking with is not presents");
        }
        bookingById.get().setPaid(true);

        return BookingFactory.toDto(bookingRepository.save(bookingById.get()));
    }

    void deleteOffer(long id){
        Optional<Offer> offerById = offerRepository.findById(id);
        if(offerById.isPresent()){
            offerRepository.delete(offerById.get());
        }
        throw new IllegalArgumentException("Offer with this id is not presents");
    }


    void deleteBooking(long id){
        Optional<Booking> bookingById = bookingRepository.findById(id);
        if(bookingById.isPresent()){
            bookingRepository.delete(bookingById.get());
        }
        throw new IllegalArgumentException("Booking with this id is not presents");
    }

}
