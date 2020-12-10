package pl.gregorymartin.touristapp.view;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.gregorymartin.touristapp.trip.*;
import pl.gregorymartin.touristapp.trip.dto.BookingUserPanel;
import pl.gregorymartin.touristapp.user.AppUser;
import pl.gregorymartin.touristapp.user.AppUserFactory;
import pl.gregorymartin.touristapp.user.AppUserService;
import pl.gregorymartin.touristapp.user.dto.UserWriteModel;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
class UserController {
    private final AppUserService userService;
    private final CommentService commentService;
    private final TripService tripService;

    UserController(final AppUserService userService, final CommentService commentService, final TripService tripService) {
        this.userService = userService;
        this.commentService = commentService;
        this.tripService = tripService;
    }

    @RequestMapping("/login")
    public String login(
            Authentication authentication
    ) {
        if(authentication != null){
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping("/signup")
    String signup(
            Authentication authentication,
            Model model
    ) {
        if(authentication != null){
            return "redirect:/";
        }

        model.addAttribute("userWriteModel", new UserWriteModel());
        return "register";
    }


    @PostMapping("/register")
    String createUser(
            Authentication authentication,
            Model model,
            UserWriteModel userWriteModel,
            HttpServletRequest request
    ) {
        if(authentication != null){
            return "redirect:/";
        }

        userService.registerAppUser(userWriteModel, request);
/*        userService.createToken(
                new AppUser(),
                request
        );*/

        model.addAttribute("user", new AppUser());
        model.addAttribute("userWriteModel", new UserWriteModel());
        return "redirect:/login";
    }
    @RequestMapping("/verify-token")
    public ModelAndView verifyToken(@RequestParam String token){
        userService.verifyToken(token);

        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/users/panel")
    String userPanel(
            Authentication authentication,
            Model model
    ){
        if(authentication == null){
            return "redirect:/login";
        }
        model.addAttribute("userWriteModel", new UserWriteModel());
        AppUser appUser = (AppUser) authentication.getPrincipal();
        model.addAttribute("user", AppUserFactory.toDto(appUser));

        List<BookingUserPanel> futureBookings = tripService.getFutureBookingsByUser(appUser.getId());
        model.addAttribute("futureBookings", futureBookings);

        List<BookingUserPanel> pastBookings = tripService.getPastBookingsByUser(appUser.getId());
        model.addAttribute("pastBookings", pastBookings);



        model.addAttribute("comments", CommentFactory.toCommentUserPanel(commentService.getCommentByUser(appUser.getId())));

        return "user-panel";
    }
}
