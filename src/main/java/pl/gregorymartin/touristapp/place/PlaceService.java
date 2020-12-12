package pl.gregorymartin.touristapp.place;

import org.springframework.data.domain.Sort;
import pl.gregorymartin.touristapp.place.dto.CityReadModel;
import pl.gregorymartin.touristapp.place.dto.CityWriteModel;
import pl.gregorymartin.touristapp.place.dto.CountryReadModel;

import java.util.List;


interface PlaceService {
    List<CityReadModel> getAllCities(int page, Sort.Direction sort, String sortBy, int items);

    List<CountryReadModel> getAllCountries(int page, Sort.Direction sort, String sortBy, int items);

    CityReadModel getCityById(long id);

    CountryReadModel getCountryById(long id);

    CityReadModel addCity(CityWriteModel cityWriteModel);

    Country addCountry(Country country);

    CityReadModel modifyCity(long id, CityWriteModel cityWriteModel);

    void deleteCity(long id);
}
