package pl.gregorymartin.touristapp.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.gregorymartin.touristapp.trip.TripService;
import pl.gregorymartin.touristapp.trip.dto.OfferSearch;
import pl.gregorymartin.touristapp.user.dto.UserWriteModel;
import pl.gregorymartin.touristapp.view.dto.SearchMVC;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Controller
class SearchOffers {
    private final TripService tripService;
    private final Logger logger = LoggerFactory.getLogger(SearchOffers.class);

    SearchOffers(final TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("/search")
    String search(
            @RequestParam(required = false) String from,
            String to,
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            Model model
    ){

        model.addAttribute("userWriteModel", new UserWriteModel());

        model.addAttribute("fromLocation", tripService.availableCitiesToDeparture());
        model.addAttribute("toLocation", tripService.availableCitiesToArrive());
        model.addAttribute("search", new SearchMVC());

        model.addAttribute("contentDesc", "From " + from + " To " + to );

        ZonedDateTime when = date.toInstant().atZone(ZoneId.systemDefault());
        List<OfferSearch> offerSearches;
        try{
            offerSearches = tripService.searchOffers(from, to, when);

        }catch (IllegalArgumentException e){
            logger.warn(e.getMessage());
            return "redirect:/";
        }
        if(offerSearches.size() <= 0){
            return "redirect:/";
        }

        model.addAttribute("offersList", offerSearches);

        return "search";
    }

    @PostMapping("/search")
    String searchContent(
            @ModelAttribute("search") SearchMVC search,
            Model model
    ){
        if(search.getDate().isBlank()){
            search.setDate(LocalDate.now().toString());
        }
        model.addAttribute("search", new SearchMVC());
        return "redirect:/search?from=" + search.getFrom() + "&to=" + search.getTo() + "&date=" + search.getDate();
    }
}
