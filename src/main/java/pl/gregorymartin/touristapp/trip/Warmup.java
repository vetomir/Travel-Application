package pl.gregorymartin.touristapp.trip;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.gregorymartin.touristapp.trip.dto.BookingWriteModel;
import pl.gregorymartin.touristapp.trip.dto.CommentWriteModel;
import pl.gregorymartin.touristapp.trip.dto.OfferWriteModel;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Component("tripWarmup")
@Lazy
class Warmup implements ApplicationListener<ContextRefreshedEvent> {
    private final TripService tripService;
    private final CommentService commentService;

    Warmup(final TripService tripService, final CommentService commentService) {
        this.tripService = tripService;
        this.commentService = commentService;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {
        putOffers();
        putBookings();
        putComments();

    }

    void putOffers(){
        List<OfferWriteModel> offers = new ArrayList<>();


        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.of(LocalDateTime.of(2019, Month.APRIL,2,2,20), ZoneId.of("UTC")),"Oslo",ZonedDateTime.of(LocalDateTime.of(2019, Month.APRIL,15,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.of(LocalDateTime.of(2016, Month.MARCH,2,2,20), ZoneId.of("UTC")),"Warsaw",ZonedDateTime.of(LocalDateTime.of(2016, Month.MARCH,11,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Oslo", ZonedDateTime.of(LocalDateTime.of(2012, Month.APRIL,2,2,20), ZoneId.of("UTC")),"Moscow",ZonedDateTime.of(LocalDateTime.of(2012, Month.APRIL,9,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.of(LocalDateTime.of(2014, Month.MARCH,2,2,20), ZoneId.of("UTC")),"London",ZonedDateTime.of(LocalDateTime.of(2014, Month.MARCH,22,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.of(LocalDateTime.of(2020, Month.APRIL,2,2,20), ZoneId.of("UTC")),"Barcelona",ZonedDateTime.of(LocalDateTime.of(2020, Month.APRIL,2,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.of(LocalDateTime.of(2022, Month.APRIL,2,2,20), ZoneId.of("UTC")),"Madrid",ZonedDateTime.of(LocalDateTime.of(2022, Month.APRIL,16,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Warsaw", ZonedDateTime.of(LocalDateTime.of(2015, Month.MAY,2,2,20), ZoneId.of("UTC")),"Cracow",ZonedDateTime.of(LocalDateTime.of(2015, Month.MAY,7,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.of(LocalDateTime.of(2011, Month.APRIL,2,2,20), ZoneId.of("UTC")),"Oslo",ZonedDateTime.of(LocalDateTime.of(2011, Month.APRIL,12,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.of(LocalDateTime.of(2030, Month.MARCH,2,2,20), ZoneId.of("UTC")),"Warsaw",ZonedDateTime.of(LocalDateTime.of(2030, Month.MARCH,21,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.of(LocalDateTime.of(2019, Month.APRIL,2,2,20), ZoneId.of("UTC")),"Oslo",ZonedDateTime.of(LocalDateTime.of(2019, Month.APRIL,15,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.of(LocalDateTime.of(2016, Month.MARCH,2,2,20), ZoneId.of("UTC")),"Warsaw",ZonedDateTime.of(LocalDateTime.of(2016, Month.MARCH,11,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Oslo", ZonedDateTime.of(LocalDateTime.of(2012, Month.APRIL,2,2,20), ZoneId.of("UTC")),"Moscow",ZonedDateTime.of(LocalDateTime.of(2012, Month.APRIL,9,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.of(LocalDateTime.of(2014, Month.MARCH,2,2,20), ZoneId.of("UTC")),"London",ZonedDateTime.of(LocalDateTime.of(2014, Month.MARCH,22,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.of(LocalDateTime.of(2020, Month.APRIL,2,2,20), ZoneId.of("UTC")),"Barcelona",ZonedDateTime.of(LocalDateTime.of(2020, Month.APRIL,2,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.of(LocalDateTime.of(2022, Month.APRIL,2,2,20), ZoneId.of("UTC")),"Madrid",ZonedDateTime.of(LocalDateTime.of(2022, Month.APRIL,16,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Warsaw", ZonedDateTime.of(LocalDateTime.of(2015, Month.MAY,2,2,20), ZoneId.of("UTC")),"Cracow",ZonedDateTime.of(LocalDateTime.of(2015, Month.MAY,7,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.of(LocalDateTime.of(2011, Month.APRIL,2,2,20), ZoneId.of("UTC")),"Oslo",ZonedDateTime.of(LocalDateTime.of(2011, Month.APRIL,12,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.of(LocalDateTime.of(2030, Month.MARCH,2,2,20), ZoneId.of("UTC")),"Warsaw",ZonedDateTime.of(LocalDateTime.of(2030, Month.MARCH,21,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.of(LocalDateTime.of(2019, Month.APRIL,2,2,20), ZoneId.of("UTC")),"Oslo",ZonedDateTime.of(LocalDateTime.of(2019, Month.APRIL,15,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.of(LocalDateTime.of(2016, Month.MARCH,2,2,20), ZoneId.of("UTC")),"Warsaw",ZonedDateTime.of(LocalDateTime.of(2016, Month.MARCH,11,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Oslo", ZonedDateTime.of(LocalDateTime.of(2012, Month.APRIL,2,2,20), ZoneId.of("UTC")),"Moscow",ZonedDateTime.of(LocalDateTime.of(2012, Month.APRIL,9,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.of(LocalDateTime.of(2014, Month.MARCH,2,2,20), ZoneId.of("UTC")),"London",ZonedDateTime.of(LocalDateTime.of(2014, Month.MARCH,22,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.of(LocalDateTime.of(2020, Month.APRIL,2,2,20), ZoneId.of("UTC")),"Barcelona",ZonedDateTime.of(LocalDateTime.of(2020, Month.APRIL,2,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Warsaw", ZonedDateTime.of(LocalDateTime.of(2022, Month.APRIL,2,2,20), ZoneId.of("UTC")),"Oslo",ZonedDateTime.of(LocalDateTime.of(2022, Month.APRIL,16,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Warsaw", ZonedDateTime.of(LocalDateTime.of(2022, Month.APRIL,2,2,20), ZoneId.of("UTC")),"London",ZonedDateTime.of(LocalDateTime.of(2022, Month.APRIL,16,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Warsaw", ZonedDateTime.of(LocalDateTime.of(2015, Month.MAY,2,2,20), ZoneId.of("UTC")),"Cracow",ZonedDateTime.of(LocalDateTime.of(2015, Month.MAY,7,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.of(LocalDateTime.of(2011, Month.APRIL,2,2,20), ZoneId.of("UTC")),"Oslo",ZonedDateTime.of(LocalDateTime.of(2011, Month.APRIL,12,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.of(LocalDateTime.of(2030, Month.MARCH,2,2,20), ZoneId.of("UTC")),"Warsaw",ZonedDateTime.of(LocalDateTime.of(2030, Month.MARCH,21,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.of(LocalDateTime.of(2019, Month.APRIL,2,2,20), ZoneId.of("UTC")),"Oslo",ZonedDateTime.of(LocalDateTime.of(2019, Month.APRIL,15,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.of(LocalDateTime.of(2016, Month.MARCH,2,2,20), ZoneId.of("UTC")),"Warsaw",ZonedDateTime.of(LocalDateTime.of(2016, Month.MARCH,11,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Oslo", ZonedDateTime.of(LocalDateTime.of(2012, Month.APRIL,2,2,20), ZoneId.of("UTC")),"Moscow",ZonedDateTime.of(LocalDateTime.of(2012, Month.APRIL,9,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.of(LocalDateTime.of(2014, Month.MARCH,2,2,20), ZoneId.of("UTC")),"London",ZonedDateTime.of(LocalDateTime.of(2014, Month.MARCH,22,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.of(LocalDateTime.of(2020, Month.APRIL,2,2,20), ZoneId.of("UTC")),"Barcelona",ZonedDateTime.of(LocalDateTime.of(2020, Month.APRIL,2,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.of(LocalDateTime.of(2022, Month.APRIL,2,2,20), ZoneId.of("UTC")),"Madrid",ZonedDateTime.of(LocalDateTime.of(2022, Month.APRIL,16,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Warsaw", ZonedDateTime.of(LocalDateTime.of(2015, Month.MAY,2,2,20), ZoneId.of("UTC")),"Cracow",ZonedDateTime.of(LocalDateTime.of(2015, Month.MAY,7,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.of(LocalDateTime.of(2011, Month.APRIL,2,2,20), ZoneId.of("UTC")),"Oslo",ZonedDateTime.of(LocalDateTime.of(2011, Month.APRIL,12,2,20), ZoneId.of("UTC")),20,70.00));
        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.of(LocalDateTime.of(2030, Month.MARCH,2,2,20), ZoneId.of("UTC")),"Warsaw",ZonedDateTime.of(LocalDateTime.of(2030, Month.MARCH,21,2,20), ZoneId.of("UTC")),20,70.00));


        offers.forEach(tripService::addOffer);



    }

    void putBookings(){
        List<BookingWriteModel> bookings = new ArrayList<>();

        bookings.add(new BookingWriteModel(1, 1, 2));
        bookings.add(new BookingWriteModel(1, 2, 2));
        bookings.add(new BookingWriteModel(3, 1, 1));
        bookings.add(new BookingWriteModel(4, 2, 1));
        bookings.add(new BookingWriteModel(5, 1, 2));
        bookings.add(new BookingWriteModel(6, 3, 1));
        bookings.add(new BookingWriteModel(7, 2, 4));
        bookings.add(new BookingWriteModel(2, 1, 1));
        bookings.add(new BookingWriteModel(1, 1, 2));
        bookings.add(new BookingWriteModel(20, 1, 2));
        bookings.add(new BookingWriteModel(15, 2, 2));
        bookings.add(new BookingWriteModel(16, 1, 1));
        bookings.add(new BookingWriteModel(14, 2, 1));
        bookings.add(new BookingWriteModel(20, 3, 2));
        bookings.add(new BookingWriteModel(16, 2, 1));
        bookings.add(new BookingWriteModel(17, 2, 4));
        bookings.add(new BookingWriteModel(12, 3, 1));
        bookings.add(new BookingWriteModel(11, 3, 2));

        bookings.stream().forEach(tripService::addBooking);

        tripService.setPaid(1,1);
        tripService.setPaid(2,2);
        tripService.setPaid(3,1);
/*        tripService.setPaid(1,3);
        tripService.setPaid(1,4);*/
    }
    @Lazy
    void putComments(){
        List<CommentWriteModel> comments = new ArrayList<>();
        comments.add(new CommentWriteModel(1L,1L,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ac nulla tincidunt, tincidunt quam et, pellentesque felis. Fusce eu ligula arcu. Proin ultricies blandit elit sit amet aliquet. Mauris porta enim et metus tincidunt dictum. Fusce eleifend augue diam, feugiat pretium orci gravida vitae"));
        comments.add(new CommentWriteModel(2L,2L,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ac nulla tincidunt, tincidunt quam et, pellentesque felis. Fusce eu ligula arcu. Proin ultricies blandit elit sit amet aliquet. Mauris porta enim et metus tincidunt dictum. Fusce eleifend augue diam, feugiat pretium orci gravida vitae"));
        comments.add(new CommentWriteModel(1L,3L,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ac nulla tincidunt, tincidunt quam et, pellentesque felis. Fusce eu ligula arcu. Proin ultricies blandit elit sit amet aliquet. Mauris porta enim et metus tincidunt dictum. Fusce eleifend augue diam, feugiat pretium orci gravida vitae"));
        comments.add(new CommentWriteModel(2L,4L,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ac nulla tincidunt, tincidunt quam et, pellentesque felis. Fusce eu ligula arcu. Proin ultricies blandit elit sit amet aliquet. Mauris porta enim et metus tincidunt dictum. Fusce eleifend augue diam, feugiat pretium orci gravida vitae"));
        comments.add(new CommentWriteModel(1L,5L,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ac nulla tincidunt, tincidunt quam et, pellentesque felis. Fusce eu ligula arcu. Proin ultricies blandit elit sit amet aliquet. Mauris porta enim et metus tincidunt dictum. Fusce eleifend augue diam, feugiat pretium orci gravida vitae"));
        comments.add(new CommentWriteModel(2L,6L,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ac nulla tincidunt, tincidunt quam et, pellentesque felis. Fusce eu ligula arcu. Proin ultricies blandit elit sit amet aliquet. Mauris porta enim et metus tincidunt dictum. Fusce eleifend augue diam, feugiat pretium orci gravida vitae"));
        comments.add(new CommentWriteModel(2L,7L,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ac nulla tincidunt, tincidunt quam et, pellentesque felis. Fusce eu ligula arcu. Proin ultricies blandit elit sit amet aliquet. Mauris porta enim et metus tincidunt dictum. Fusce eleifend augue diam, feugiat pretium orci gravida vitae"));

        comments.forEach(commentService::addComment);
    }
}
