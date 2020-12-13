package pl.gregorymartin.touristapp.place.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityWriteModel {
    private String name;
    private String photoUrl;
    private String country;
}
