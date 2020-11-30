package pl.gregorymartin.touristapp.trip;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gregorymartin.touristapp.trip.dto.BookingReadModel;
import pl.gregorymartin.touristapp.trip.dto.BookingWriteModel;
import pl.gregorymartin.touristapp.trip.dto.OfferReadModel;
import pl.gregorymartin.touristapp.trip.dto.OfferWriteModel;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/trips")
class TripController {
    private final TripService tripService;

    TripController(final TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("/offers/list")
    ResponseEntity<List<OfferReadModel>> readAllOffers(@RequestParam(required = false) Integer page, Sort.Direction sort, String sortBy
            /*@AuthenticationPrincipal UsernamePasswordAuthenticationToken user*/){
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        String sortByVariable = sortBy != null ? sortBy : "id";

        return ResponseEntity.ok(tripService.getAllOffers(pageNumber, sortDirection, sortByVariable, 25));
    }

    @GetMapping("/bookings/list")
    ResponseEntity<List<BookingReadModel>> readAllBookings(@RequestParam(required = false) Integer page, Sort.Direction sort, String sortBy
            /*@AuthenticationPrincipal UsernamePasswordAuthenticationToken user*/){
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        String sortByVariable = sortBy != null ? sortBy : "id";

        return ResponseEntity.ok(tripService.getAllBookings(pageNumber, sortDirection, sortByVariable, 25));
    }

    @GetMapping("/offers")
    public ResponseEntity<OfferReadModel> readOffer(@RequestParam long id/*, @RequestParam(name = "user-id") long userId*/) {
        OfferReadModel offerReadModel = tripService.getOfferById(id);
        return ResponseEntity.ok(offerReadModel);
    }

    @GetMapping("/bookings")
    public ResponseEntity<BookingReadModel> readBooking(@RequestParam long id/*, @RequestParam(name = "user-id") long userId*/) {
        BookingReadModel bookingReadModel = tripService.getBookingById(id);
        return ResponseEntity.ok(bookingReadModel);
    }

    @PostMapping("/offers")
    public ResponseEntity<OfferReadModel> createOffer(@RequestBody OfferWriteModel offerWriteModel/*, @RequestParam(name = "user-id") long userId*/) {
        OfferReadModel offerReadModel = tripService.addOffer(offerWriteModel);
        return ResponseEntity.created(URI.create("/" + offerReadModel.getId())).body(offerReadModel);
    }

    @PostMapping("/bookings")
    public ResponseEntity<BookingReadModel> createBooking(@RequestBody BookingWriteModel bookingWriteModel/*, @RequestParam(name = "user-id") long userId*/) {
        BookingReadModel bookingReadModel = tripService.addBooking(bookingWriteModel);
        return ResponseEntity.created(URI.create("/" + bookingReadModel.getId())).body(bookingReadModel);
    }

    @PatchMapping("/offers")
    public ResponseEntity<OfferReadModel> updateOffer(@RequestParam long id, @RequestBody OfferWriteModel offerWriteModel/*, @RequestParam(name = "user-id") long userId*/) {
        OfferReadModel offerReadModel = tripService.modifyOffer(id, offerWriteModel);
        return ResponseEntity.created(URI.create("/" + offerReadModel.getId())).body(offerReadModel);
    }

    @PatchMapping("/bookings")
    public ResponseEntity<BookingReadModel> updateBooking(@RequestParam long id, @RequestBody BookingWriteModel bookingWriteModel/*, @RequestParam(name = "user-id") long userId*/) {
        BookingReadModel bookingReadModel = tripService.modifyBooking(id, bookingWriteModel);
        return ResponseEntity.created(URI.create("/" + bookingReadModel.getId())).body(bookingReadModel);
    }

    @DeleteMapping("/offers")
    public ResponseEntity deleteOffer(@RequestParam long id) {
        tripService.deleteOffer(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/bookings")
    public ResponseEntity deleteBooking(@RequestParam long id) {
        tripService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}
