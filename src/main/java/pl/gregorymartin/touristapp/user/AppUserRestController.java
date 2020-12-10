package pl.gregorymartin.touristapp.user;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.gregorymartin.touristapp.user.dto.UserReadModel;
import pl.gregorymartin.touristapp.user.dto.UserWriteModel;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
class AppUserRestController {
    private final AppUserService appUserService;

    AppUserRestController(final AppUserService appUserService) {
        this.appUserService = appUserService;
    }



    @GetMapping("/list")
    ResponseEntity<List<UserReadModel>> readAllUsers(@RequestParam(required = false) Integer page, Sort.Direction sort, String sortBy
            /*@AuthenticationPrincipal UsernamePasswordAuthenticationToken user*/){
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        String sortByVariable = sortBy != null ? sortBy : "id";

        return ResponseEntity.ok(appUserService.getAllAppUsers(pageNumber, sortDirection, sortByVariable, 25));
    }

    @GetMapping
    public ResponseEntity<UserReadModel> readOffer(@RequestParam long id/*, @RequestParam(name = "user-id") long userId*/) {
        UserReadModel user = appUserService.getAppUserById(id);
        return ResponseEntity.ok(user);
    }


    @PostMapping
    public ResponseEntity<UserReadModel> createAppUser(
            @RequestBody UserWriteModel userWriteModel,
            HttpServletRequest request
    ) {
        UserReadModel user = appUserService.registerAppUser(userWriteModel, request);
        return ResponseEntity.created(URI.create("/" + user.getId())).body(user);
    }

    @PatchMapping
    public ResponseEntity<UserReadModel> updateAppUser(
            @RequestParam long id,
            @RequestBody UserWriteModel userWriteModel
            /*, @RequestParam(name = "user-id") long userId*/
    ) {
        UserReadModel offerReadModel = appUserService.modifyAppUser(id, userWriteModel);
        return ResponseEntity.created(URI.create("/" + offerReadModel.getId())).body(offerReadModel);
    }

    @PatchMapping("/photo")
    public ResponseEntity<UserReadModel> updateAppUserPhoto(
            @RequestParam long id,
            @RequestHeader String photo
            /*, @RequestParam(name = "user-id") long userId*/
    ) {
        UserReadModel offerReadModel = appUserService.changePhoto(id, photo);
        return ResponseEntity.created(URI.create("/" + offerReadModel.getId())).body(offerReadModel);
    }

    @PatchMapping("role")
    public ResponseEntity<UserReadModel> updateRole(
            @RequestParam long id
            /*, @RequestParam(name = "user-id") long userId*/
    ) {
        UserReadModel offerReadModel = appUserService.toggleRole(id);
        return ResponseEntity.created(URI.create("/" + offerReadModel.getId())).body(offerReadModel);
    }

    @DeleteMapping
    public ResponseEntity deleteAppUser(@RequestParam long id) {
        appUserService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/elo")
    String elo(Authentication authentication){
        AppUser user = (AppUser) authentication.getPrincipal();
        return user.getUsername();
    }
}
