package pl.gregorymartin.touristapp.view;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.gregorymartin.touristapp.trip.CommentService;
import pl.gregorymartin.touristapp.trip.TripService;
import pl.gregorymartin.touristapp.trip.dto.CommentWriteModel;
import pl.gregorymartin.touristapp.user.AppUser;
import pl.gregorymartin.touristapp.user.dto.UserWriteModel;

@Controller
@RequestMapping("/booking/review")
class ReviewController {
    private final CommentService commentService;
    private final TripService tripService;

    ReviewController(final CommentService commentService, final TripService tripService) {
        this.commentService = commentService;
        this.tripService = tripService;
    }

    @GetMapping("/{id}")
    String createReview(
            @PathVariable("id") long offerId,
            Model model,
            Authentication authentication
    ){
        if(authentication == null){
            return "login";
        }
        AppUser user = (AppUser) authentication.getPrincipal();
        model.addAttribute("userWriteModel", new UserWriteModel());

        model.addAttribute("offerId", offerId);
        model.addAttribute("offer", tripService.getOfferById(offerId));


        model.addAttribute("review", new CommentWriteModel());

        return "review";
    }
    @PostMapping("/{id}/add")
    String addReview(
            @ModelAttribute("review") CommentWriteModel commentWriteModel,
            @PathVariable("id") long offerId,
            Model model,
            Authentication authentication){
        if(authentication == null){
            return "login";
        }
        AppUser user = (AppUser) authentication.getPrincipal();
        commentWriteModel.setUserId(user.getId());
        commentWriteModel.setOfferId(offerId);
        commentService.addComment(commentWriteModel);


        return "redirect:/users/panel";
    }
}
