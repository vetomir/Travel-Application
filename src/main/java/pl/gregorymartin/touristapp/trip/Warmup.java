package pl.gregorymartin.touristapp.trip;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.gregorymartin.touristapp.trip.dto.BookingWriteModel;
import pl.gregorymartin.touristapp.trip.dto.OfferWriteModel;
import pl.gregorymartin.touristapp.trip.dto.CommentWriteModel;

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

        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.now(),"Warsaw",ZonedDateTime.now(),20,70.00));
        offers.add( new OfferWriteModel("London", ZonedDateTime.now(),"Paris",ZonedDateTime.now(),20,24.00));
        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.now(),"Wroclaw",ZonedDateTime.now(),20,15.00));
        offers.add( new OfferWriteModel("Poznan", ZonedDateTime.now(),"Cracow",ZonedDateTime.now(),20,19.99));
        offers.add( new OfferWriteModel("Warsaw", ZonedDateTime.now(),"Moscow",ZonedDateTime.now(),20,54.99));
        offers.add( new OfferWriteModel("Oslo", ZonedDateTime.now(),"Paris",ZonedDateTime.now(),20,80.00));
        offers.add( new OfferWriteModel("Paris", ZonedDateTime.now(),"Oslo",ZonedDateTime.now(),20,40.00));
        offers.add( new OfferWriteModel("Cracow", ZonedDateTime.now(),"Warsaw",ZonedDateTime.now(),20,14.99));

        offers.stream().forEach(tripService::addOffer);

    }

    void putBookings(){
        List<BookingWriteModel> bookings = new ArrayList<>();

        bookings.add(new BookingWriteModel(1, 1, 2));
        bookings.add(new BookingWriteModel(1, 2, 2));
        bookings.add(new BookingWriteModel(3, 1, 1));
        bookings.add(new BookingWriteModel(4, 2, 1));
        bookings.add(new BookingWriteModel(5, 1, 2));
        bookings.add(new BookingWriteModel(6, 2, 1));
        bookings.add(new BookingWriteModel(7, 2, 4));
        bookings.add(new BookingWriteModel(2, 1, 1));
        bookings.add(new BookingWriteModel(1, 1, 2));

        bookings.stream().forEach(tripService::addBooking);
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
