package pl.gregorymartin.touristapp.place;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gregorymartin.touristapp.place.dto.CityReadModel;
import pl.gregorymartin.touristapp.place.dto.CityWriteModel;
import pl.gregorymartin.touristapp.place.dto.CountryReadModel;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/places")
class PlaceController {
    private final PlaceService placeService;

    PlaceController(final PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/list")
    ResponseEntity<List<CityReadModel>> readAllCities(@RequestParam(required = false) Integer page, Sort.Direction sort, String sortBy
            /*@AuthenticationPrincipal UsernamePasswordAuthenticationToken user*/){
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        String sortByVariable = sortBy != null ? sortBy : "id";

        return ResponseEntity.ok(placeService.getAllCities(pageNumber, sortDirection, sortByVariable, 25));
    }

    @GetMapping("/countries/list")
    ResponseEntity<List<CountryReadModel>> readAllCountries(@RequestParam(required = false) Integer page, Sort.Direction sort, String sortBy
            /*@AuthenticationPrincipal UsernamePasswordAuthenticationToken user*/){
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        String sortByVariable = sortBy != null ? sortBy : "id";

        return ResponseEntity.ok(placeService.getAllCountries(pageNumber, sortDirection, sortByVariable, 25));
    }


    @GetMapping
    public ResponseEntity<CityReadModel> readCity(@RequestParam long id/*, @RequestParam(name = "user-id") long userId*/) {
        CityReadModel cityReadModel = placeService.getCityById(id);
        return ResponseEntity.ok(cityReadModel);
    }

    @PostMapping
    public ResponseEntity<CityReadModel> createCity(@RequestBody CityWriteModel cityWriteModel/*, @RequestParam(name = "user-id") long userId*/) {
        CityReadModel cityReadModel = placeService.addCity(cityWriteModel);
        return ResponseEntity.created(URI.create("/" + cityReadModel.getId())).body(cityReadModel);
    }

    @PatchMapping
    public ResponseEntity<CityReadModel> updateCity(@RequestParam long id, @RequestBody CityWriteModel cityWriteModel/*, @RequestParam(name = "user-id") long userId*/) {
        CityReadModel cityReadModel = placeService.modifyCity(id, cityWriteModel);
        return ResponseEntity.created(URI.create("/" + cityReadModel.getId())).body(cityReadModel);
    }

    @DeleteMapping
    public ResponseEntity deletePost(@RequestParam long id) {
        placeService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }
}
