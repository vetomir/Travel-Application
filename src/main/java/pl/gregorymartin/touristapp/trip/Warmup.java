package pl.gregorymartin.touristapp.trip;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.gregorymartin.touristapp.trip.dto.BookingWriteModel;
import pl.gregorymartin.touristapp.trip.dto.OfferWriteModel;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Component("tripWarmup")
@Lazy
class Warmup implements ApplicationListener<ContextRefreshedEvent> {
    private final TripService tripService;

    Warmup(final TripService tripService) {
        this.tripService = tripService;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {
        putOffers();
        putBookings();

    }

    void putOffers(){
        List<OfferWriteModel> offers = new ArrayList<>();

        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.now(),"Warsaw",ZonedDateTime.now(),20,70.00));
        offers.add( new OfferWriteModel("London", ZonedDateTime.now(),"Paris",ZonedDateTime.now(),20,24.00));
        offers.add( new OfferWriteModel("Poznan", ZonedDateTime.now(),"Warsaw",ZonedDateTime.now(),20,200.00));

        offers.stream().forEach(tripService::addOffer);

    }

    void putBookings(){
        List<BookingWriteModel> bookings = new ArrayList<>();

        bookings.add(new BookingWriteModel(1, 1, 2));
        bookings.add(new BookingWriteModel(1, 1, 2));
        bookings.add(new BookingWriteModel(3, 1, 10));

        bookings.stream().forEach(tripService::addBooking);
    }
}
