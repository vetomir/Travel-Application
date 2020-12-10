package pl.gregorymartin.touristapp.view;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.gregorymartin.touristapp.trip.CommentService;
import pl.gregorymartin.touristapp.trip.TripService;
import pl.gregorymartin.touristapp.trip.dto.CommentReadModel;
import pl.gregorymartin.touristapp.trip.dto.OfferSearch;
import pl.gregorymartin.touristapp.user.dto.UserWriteModel;
import pl.gregorymartin.touristapp.view.dto.SearchMVC;

import java.security.Principal;
import java.util.List;

@Controller
class HomepageController {
    private final TripService tripService;
    private final CommentService commentService;

    HomepageController(final TripService tripService, final CommentService commentService) {
        this.tripService = tripService;
        this.commentService = commentService;
    }

    @GetMapping
    String homepage(
            Model model,
            Principal principal
    ){
        //auth
        boolean userIsPresent = false;
        if(principal != null){
            userIsPresent = true;
        }
        model.addAttribute("userIsPresent", userIsPresent);
        model.addAttribute("userWriteModel", new UserWriteModel());

        //header


        //search
        model.addAttribute("fromLocation", tripService.availableCitiesToDeparture());
        model.addAttribute("toLocation", tripService.availableCitiesToArrive());
        model.addAttribute("search", new SearchMVC());

        //content
        List<OfferSearch> topOffers =
                tripService.getTopOffers(0, Sort.Direction.DESC, "id", 6);
        model.addAttribute("topOffers", topOffers);

        //coments
        List<CommentReadModel> newComments =
                commentService.getAllComments(0, Sort.Direction.DESC, "id", 6);
        model.addAttribute("newComments", newComments);

        //test
        model.addAttribute("elo", "TEST");

        return "index";
    }
}
