package pl.gregorymartin.touristapp.place.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public
class CountryReadModel {
    private long id;
    private String name;
    private String photoUrl;
    private List<String> cities;
}
