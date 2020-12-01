package pl.gregorymartin.touristapp.user;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.gregorymartin.touristapp.user.dto.UserReadModel;

import java.util.List;

@RestController
@RequestMapping("/api/users")
class AppUserController {
    private final AppUserService appUserService;

    AppUserController(final AppUserService appUserService) {
        this.appUserService = appUserService;
    }



    @GetMapping("/list")
    ResponseEntity<List<UserReadModel>> readAllUsers(@RequestParam(required = false) Integer page, Sort.Direction sort, String sortBy
            /*@AuthenticationPrincipal UsernamePasswordAuthenticationToken user*/){
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        String sortByVariable = sortBy != null ? sortBy : "id";

        return ResponseEntity.ok(appUserService.getAllUsers(pageNumber, sortDirection, sortByVariable, 25));
    }
}
