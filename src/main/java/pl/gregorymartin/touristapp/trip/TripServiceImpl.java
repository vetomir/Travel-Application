package pl.gregorymartin.touristapp.trip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gregorymartin.touristapp.place.City;
import pl.gregorymartin.touristapp.place.CityRepository;
import pl.gregorymartin.touristapp.trip.dto.*;
import pl.gregorymartin.touristapp.user.AppUser;
import pl.gregorymartin.touristapp.user.AppUserRepository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class TripServiceImpl implements TripService{
    private Logger logger = LoggerFactory.getLogger(TripService.class);

    private SqlBookingRepository bookingRepository;
    private SqlOfferRepository offerRepository;
    private CityRepository cityRepository;
    private AppUserRepository appUserRepository;

    TripServiceImpl(final SqlBookingRepository bookingRepository, final SqlOfferRepository offerRepository, final CityRepository cityRepository, final AppUserRepository appUserRepository) {
        this.bookingRepository = bookingRepository;
        this.offerRepository = offerRepository;
        this.cityRepository = cityRepository;
        this.appUserRepository = appUserRepository;
    }

    public List<OfferReadModel> getAllOffers(int page, Sort.Direction sort, String sortBy, int items){
        List<Offer> offers = offerRepository.findAll(
                PageRequest.of(page, items,
                        Sort.by(sort, sortBy)
                )).getContent();
        return OfferFactory.toDto(offers);
    }
    public List<String> availableCitiesToArrive(){
        List<Offer> offers = offerRepository.findAll();

        return offers.stream().map(Offer::getArrivingCityName).distinct().collect(Collectors.toList());
    }
    public List<String> availableCitiesToDeparture(){
        List<Offer> offers = offerRepository.findAll();

        return offers.stream().map(Offer::getDepartureCityName).distinct().collect(Collectors.toList());
    }

    public List<OfferSearch> searchOffers(String from, String to, ZonedDateTime when){

        if(to.equals(from)){
            throw new IllegalArgumentException("Wrong query");
        }
        if (to.equals("Anywhere")){
            return OfferFactory.toOfferSearch(offerRepository.searchOffersWithoutTo(from,when));
        }
        if(from.equals("Anywhere")){
            return OfferFactory.toOfferSearch(offerRepository.searchOffersWithoutFrom(to,when));
        }
        else
            return OfferFactory.toOfferSearch(offerRepository.searchOffers(from,to,when));
    }
    public List<BookingUserPanel> getFutureBookingsByUser(long userId){
        List<Booking> bookings = bookingRepository.findAllByUserIdAndSortByDepartureDateFuture(userId, ZonedDateTime.now());
        return BookingFactory.toBookingUserPanel(bookings);
    }
    public List<BookingUserPanel> getPastBookingsByUser(long userId){
        List<Booking> bookings = bookingRepository.findAllByUserIdAndSortByDepartureDatePast(userId, ZonedDateTime.now());
        return BookingFactory.toBookingUserPanel(bookings);
    }

    public List<OfferSearch> getTopOffers(int page, Sort.Direction sort, String sortBy, int items){
        List<Offer> offers = offerRepository.findAll(
                PageRequest.of(page, items,
                        Sort.by(sort, sortBy)
                )).getContent();
        return OfferFactory.toOfferSearch(offers);
    }

    public List<BookingReadModel> getAllBookings(int page, Sort.Direction sort, String sortBy, int items){
        List<Booking> offers = bookingRepository.findAll(
                PageRequest.of(page, items,
                        Sort.by(sort, sortBy)
                )).getContent();
        return BookingFactory.toDto(offers);
    }


    public OfferReadModel getOfferById(long id){
        Optional<Offer> offer = offerRepository.findById(id);
        if (offer.isEmpty()){
            throw new IllegalArgumentException("Offer is not presents");
        }

        return OfferFactory.toDto(offer.get());
    }

    public BookingReadModel getBookingById(long id){
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isEmpty()){
            throw new IllegalArgumentException("Booking is not presents");
        }
        return BookingFactory.toDto(booking.get());
    }

    public OfferReadModel addOffer(OfferWriteModel offerWriteModel){
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
        offer.setArrivingCityPhotoUrl(arriving.get().getPhotoUrl());

        return OfferFactory.toDto(offerRepository.save(offer));
    }

    @Transactional
    public BookingReadModel addBooking(BookingWriteModel bookingWriteModel){
        Booking booking = BookingFactory.toEntity(bookingWriteModel);
        Optional<AppUser> appUser = appUserRepository.findById(bookingWriteModel.getUserId());
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
        logger.info("Create booking for user: {} for {} people, offer id = {}", appUser.get().getUsername(), bookingWriteModel.getSize(), bookingWriteModel.getOfferId());
        offerRepository.save(offer.get());

        return BookingFactory.toDto(bookingRepository.save(booking));
    }

    @Transactional
    public OfferReadModel modifyOffer(long id, OfferWriteModel offerWriteModel){
        Optional<Offer> offerById = offerRepository.findById(id);
        if(offerById.isPresent()){
            Offer offer= OfferFactory.toEntity(offerWriteModel);
            offer.setId(id);

            return OfferFactory.toDto(offerRepository.save(offer));
        }
        throw new IllegalArgumentException("Offer with is not presents");
    }

    @Transactional
    public BookingReadModel modifyBooking(long id, BookingWriteModel bookingWriteModel){
        Optional<Booking> bookingById = bookingRepository.findById(id);
        if(bookingById.isEmpty()){
            throw new IllegalArgumentException("Booking with is not presents");
        }
        Booking booking= BookingFactory.toEntity(bookingWriteModel);
        booking.setId(id);

        return BookingFactory.toDto(bookingRepository.save(booking));
    }

    @Transactional
    public BookingReadModel setPaid(long id, long userId){
        Optional<Booking> bookingById = bookingRepository.findById(id);
        if(bookingById.isEmpty()){
            throw new IllegalArgumentException("Booking with is not presents");
        }
        if(bookingById.get().getAppUser().getId() != userId){
            throw new IllegalArgumentException("User is not correct");
        }
        if(bookingById.get().isPaid()){
            throw new IllegalArgumentException("Booking " + id + " is already paid");
        }

        bookingById.get().setPaid(true);
        logger.info("booking id: {}, is paid", id);

        return BookingFactory.toDto(bookingRepository.save(bookingById.get()));
    }

    public void deleteOffer(long id){
        Optional<Offer> offerById = offerRepository.findById(id);
        if(offerById.isPresent()){
            offerRepository.delete(offerById.get());
        }
        throw new IllegalArgumentException("Offer with this id is not presents");
    }


    public void deleteBooking(long id){
        Optional<Booking> bookingById = bookingRepository.findById(id);
        if(bookingById.isPresent()){
            bookingRepository.delete(bookingById.get());
        }
        throw new IllegalArgumentException("Booking with this id is not presents");
    }

}
