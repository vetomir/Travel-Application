package pl.gregorymartin.touristapp.trip;

import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gregorymartin.touristapp.trip.dto.*;

import java.net.URI;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/trips")
class TripRestController {
    private final TripService tripServiceImpl;

    TripRestController(final TripService tripServiceImpl) {
        this.tripServiceImpl = tripServiceImpl;
    }

    @GetMapping("/offers/list")
    ResponseEntity<List<OfferReadModel>> readAllOffers(@RequestParam(required = false) Integer page, Sort.Direction sort, String sortBy
            /*@AuthenticationPrincipal UsernamePasswordAuthenticationToken user*/){
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        String sortByVariable = sortBy != null ? sortBy : "id";

        return ResponseEntity.ok(tripServiceImpl.getAllOffers(pageNumber, sortDirection, sortByVariable, 25));
    }

    @GetMapping("/search")
    ResponseEntity<List<OfferSearch>> searchOffers(String from, String to, @DateTimeFormat(pattern = "dd.MM.yyyy") Date date){
        ZonedDateTime when = date.toInstant().atZone(ZoneId.systemDefault());


        return ResponseEntity.ok(tripServiceImpl.searchOffers(from,to,when));
    }

    @GetMapping("/bookings/list")
    ResponseEntity<List<BookingReadModel>> readAllBookings(@RequestParam(required = false) Integer page, Sort.Direction sort, String sortBy
            /*@AuthenticationPrincipal UsernamePasswordAuthenticationToken user*/){
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        String sortByVariable = sortBy != null ? sortBy : "id";

        return ResponseEntity.ok(tripServiceImpl.getAllBookings(pageNumber, sortDirection, sortByVariable, 25));
    }

    @GetMapping("/offers")
    public ResponseEntity<OfferReadModel> readOffer(@RequestParam long id/*, @RequestParam(name = "user-id") long userId*/) {
        OfferReadModel offerReadModel = tripServiceImpl.getOfferById(id);
        return ResponseEntity.ok(offerReadModel);
    }

    @GetMapping("/bookings")
    public ResponseEntity<BookingReadModel> readBooking(@RequestParam long id/*, @RequestParam(name = "user-id") long userId*/) {
        BookingReadModel bookingReadModel = tripServiceImpl.getBookingById(id);
        return ResponseEntity.ok(bookingReadModel);
    }

    @PostMapping("/offers")
    public ResponseEntity<OfferReadModel> createOffer(@RequestBody OfferWriteModel offerWriteModel/*, @RequestParam(name = "user-id") long userId*/) {
        OfferReadModel offerReadModel = tripServiceImpl.addOffer(offerWriteModel);
        return ResponseEntity.created(URI.create("/" + offerReadModel.getId())).body(offerReadModel);
    }

    @PostMapping("/bookings")
    public ResponseEntity<BookingReadModel> createBooking(@RequestBody BookingWriteModel bookingWriteModel/*, @RequestParam(name = "user-id") long userId*/) {
        BookingReadModel bookingReadModel = tripServiceImpl.addBooking(bookingWriteModel);
        return ResponseEntity.created(URI.create("/" + bookingReadModel.getId())).body(bookingReadModel);
    }

    @PatchMapping("/offers")
    public ResponseEntity<OfferReadModel> updateOffer(@RequestParam long id, @RequestBody OfferWriteModel offerWriteModel/*, @RequestParam(name = "user-id") long userId*/) {
        OfferReadModel offerReadModel = tripServiceImpl.modifyOffer(id, offerWriteModel);
        return ResponseEntity.created(URI.create("/" + offerReadModel.getId())).body(offerReadModel);
    }

    @PatchMapping("/bookings")
    public ResponseEntity<BookingReadModel> updateBooking(@RequestParam long id, @RequestBody BookingWriteModel bookingWriteModel/*, @RequestParam(name = "user-id") long userId*/) {
        BookingReadModel bookingReadModel = tripServiceImpl.modifyBooking(id, bookingWriteModel);
        return ResponseEntity.created(URI.create("/" + bookingReadModel.getId())).body(bookingReadModel);
    }

    //todo
    @PatchMapping("/bookings/pay")
    public ResponseEntity<BookingReadModel> payBooking(@RequestParam long id/*, @RequestParam(name = "user-id") long userId*/) {
        BookingReadModel bookingReadModel = tripServiceImpl.setPaid(id, 3);
        return ResponseEntity.created(URI.create("/" + bookingReadModel.getId())).body(bookingReadModel);
    }

    @DeleteMapping("/offers")
    public ResponseEntity deleteOffer(@RequestParam long id) {
        tripServiceImpl.deleteOffer(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/bookings")
    public ResponseEntity deleteBooking(@RequestParam long id) {
        tripServiceImpl.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}
