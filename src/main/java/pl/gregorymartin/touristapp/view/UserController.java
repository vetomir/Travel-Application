package pl.gregorymartin.touristapp.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.gregorymartin.touristapp.user.AppUser;
import pl.gregorymartin.touristapp.user.AppUserService;
import pl.gregorymartin.touristapp.user.AppUserServiceImpl;
import pl.gregorymartin.touristapp.user.dto.UserWriteModel;

import javax.servlet.http.HttpServletRequest;

@Controller
class UserController {
    private final AppUserService userService;

    UserController(final AppUserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    String signup(
            Model model
    ) {

        model.addAttribute("userWriteModel", new UserWriteModel());
        return "register";
    }

    @PostMapping("/register")
    String createUser(
            Model model,
            UserWriteModel userWriteModel,
            HttpServletRequest request
    ) {
        userService.registerAppUser(userWriteModel, request);
        userService.createToken(
                new AppUser(),
                request
        );

        model.addAttribute("user", new AppUser());
        model.addAttribute("userWriteModel", new UserWriteModel());
        return "redirect:/login";
    }
    @RequestMapping("/verify-token")
    public ModelAndView verifyToken(@RequestParam String token){
        userService.verifyToken(token);

        return new ModelAndView("redirect:/login");
    }
}
