package pl.gregorymartin.touristapp.place;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gregorymartin.touristapp.place.dto.CityReadModel;
import pl.gregorymartin.touristapp.place.dto.CityWriteModel;
import pl.gregorymartin.touristapp.place.dto.CountryReadModel;

import java.util.List;
import java.util.Optional;

@Service
class PlaceService {
    private static final int PAGE_SIZE = 25;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    PlaceService(final SqlCityRepository cityRepository, final SqlCountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    List<CityReadModel> getAllCities(int page, Sort.Direction sort, String sortBy, int items){
        List<City> cities = cityRepository.findAll(
                PageRequest.of(page, items,
                        Sort.by(sort, sortBy)
                )).getContent();
        return PlaceFactory.toDto(cities);
    }

    List<CountryReadModel> getAllCountries(int page, Sort.Direction sort, String sortBy, int items){
        List<Country> countries = countryRepository.findAll(
                PageRequest.of(page, items,
                        Sort.by(sort, sortBy)
                )).getContent();
        return PlaceFactory.countryToDto(countries);
    }

    CityReadModel getCityById(long id){
        Optional<City> city = cityRepository.findById(id);
        if (city.isEmpty()){
            throw new IllegalArgumentException("City is not presents");
        }
        return PlaceFactory.toDto(city.get());
    }

    CountryReadModel getCountryById(long id){
        Optional<Country> country = countryRepository.findById(id);
        if (country.isEmpty()){
            throw new IllegalArgumentException("City is not presents");
        }
        return PlaceFactory.countryToDto(country.get());
    }


    CityReadModel addCity(CityWriteModel cityWriteModel){
        City city = PlaceFactory.toEntity(cityWriteModel);
        city.setCountry(addCountry(city.getCountry()));

        return PlaceFactory.toDto(cityRepository.save(city));
    }

    Country addCountry(Country country){
        Optional<Country> countryByName = countryRepository.findByName(country.getName());
        if(countryByName.isEmpty()){
            return countryRepository.save(country);
        }
        return countryByName.get();
    }

    @Transactional
    CityReadModel modifyCity(long id, CityWriteModel cityWriteModel){
        Optional<City> cityById = cityRepository.findById(id);
        if(cityById.isPresent()){
            City city= PlaceFactory.toEntity(cityWriteModel);
            city.setId(id);
            city.setCountry(addCountry(city.getCountry()));

            return PlaceFactory.toDto(cityRepository.save(city));
        }
        throw new IllegalArgumentException("City with is not presents");
    }

    void deleteCity(long id){
        Optional<City> cityById = cityRepository.findById(id);
        if(cityById.isPresent()){
            Country countryByName = countryRepository.findByName(cityById.get().getCountry().getName()).get();
            if (countryByName.getCities().size() == 1){
                countryRepository.deleteById(countryByName.getId());
            }
            cityRepository.delete(cityById.get());
        }
        throw new IllegalArgumentException("City with this id is not presents");
    }


}
