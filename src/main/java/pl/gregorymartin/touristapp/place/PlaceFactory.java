package pl.gregorymartin.touristapp.place;

import pl.gregorymartin.touristapp.place.dto.CityReadModel;
import pl.gregorymartin.touristapp.place.dto.CityWriteModel;
import pl.gregorymartin.touristapp.place.dto.CountryReadModel;

import java.util.List;
import java.util.stream.Collectors;

class PlaceFactory {

    //write
    public static List<City> toEntity(List<CityWriteModel> cityWriteModel) {
        return cityWriteModel.stream()
                .map(PlaceFactory::toEntity)
                .collect(Collectors.toList());
    }

    public static City toEntity(CityWriteModel cityWriteModel) {
        City city = new City();
        city.setName(cityWriteModel.getName());
        city.setPhotoUrl(cityWriteModel.getPhotoUrl());
        city.setCountry(new Country(cityWriteModel.getCountry()));

        return city;
    }

    //read

    public static List<CityReadModel> toDto(List<City> posts) {
        return posts.stream()
                .map(PlaceFactory::toDto)
                .collect(Collectors.toList());
    }

    public static CityReadModel toDto(City post) {
        return CityReadModel.builder()
                .id(post.getId())
                .name(post.getName())
                .photoUrl(post.getPhotoUrl())
                .country(post.getCountry().getName())
                .build();
    }

    public static List<CountryReadModel> countryToDto(List<Country> posts) {
        return posts.stream()
                .map(PlaceFactory::countryToDto)
                .collect(Collectors.toList());
    }

    public static CountryReadModel countryToDto(Country post) {
        return CountryReadModel.builder()
                .id(post.getId())
                .name(post.getName())
                .photoUrl(post.getPhotoUrl())
                .cities(post.getCities().stream().map(x -> x.getName()).collect(Collectors.toList()))
                .build();
    }
}
