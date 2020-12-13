package pl.gregorymartin.touristapp.view;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.gregorymartin.touristapp.trip.TripService;
import pl.gregorymartin.touristapp.trip.dto.BookingWriteModel;
import pl.gregorymartin.touristapp.user.AppUser;
import pl.gregorymartin.touristapp.user.dto.UserWriteModel;


import javax.validation.Valid;

@Controller
@RequestMapping("/booking")
class BookingController {
    private final TripService tripService;

    BookingController(final TripService tripService) {
        this.tripService = tripService;
    }

/*    @GetMapping
    String getBooking(
            Model model
    ) {

        model.addAttribute("userWriteModel", new UserWriteModel());
        return "book";
    }*/

    @GetMapping
    String booking(
            Authentication authentication,
            Model model,
            @RequestParam long offerId
    ) {
        if(authentication == null){
            return "redirect:/login";
        }
        model.addAttribute("userWriteModel", new UserWriteModel());
        model.addAttribute("offerId", offerId);
        model.addAttribute("bookingWriteModel", new BookingWriteModel());
        return "book";
    }


    @PostMapping("/{id}")
    String createBooking(
            @PathVariable(name = "id") long offerId,
            @Valid BookingWriteModel bookingWriteModel,
            Authentication authentication
    ) {
        if(authentication == null){
            return "redirect:/login";
        }
        AppUser appUser = (AppUser) authentication.getPrincipal();
        bookingWriteModel.setUserId(appUser.getId());

        bookingWriteModel.setOfferId(offerId);

        tripService.addBooking(bookingWriteModel);
        return "redirect:/users/panel";
    }

    @PostMapping("/pay/{id}")
    String payBooking(
            @PathVariable(name = "id") long bookingId,
            Authentication authentication
    ) {
        AppUser appUser = (AppUser) authentication.getPrincipal();
        tripService.setPaid(bookingId, appUser.getId());
        return "redirect:/users/panel";
    }
}
