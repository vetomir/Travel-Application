package pl.gregorymartin.touristapp.view;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.gregorymartin.touristapp.trip.TripService;
import pl.gregorymartin.touristapp.trip.dto.OfferMvcHomepage;
import pl.gregorymartin.touristapp.trip.dto.OfferReadModel;

import java.util.List;

@Controller
class HomepageController {
    private final TripService tripService;

    HomepageController(final TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping
    String homepage(Model model){

        List<OfferMvcHomepage> topOffers =
                tripService.getTopOffers(0, Sort.Direction.DESC, "id", 6);


        model.addAttribute("topOffers", topOffers);
        model.addAttribute("elo", "elo");
        return "index";
    }
}
