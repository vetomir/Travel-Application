package pl.gregorymartin.touristapp.place.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public
class CityReadModel {
    private long id;
    private String name;
    private String photoUrl;
    private String country;
}
