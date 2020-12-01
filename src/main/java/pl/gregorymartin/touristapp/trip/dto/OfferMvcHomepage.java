package pl.gregorymartin.touristapp.trip.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public
class OfferMvcHomepage {
    private String departureCityName;
    private ZonedDateTime departureTime;
    private String arrivingCityName;
    private String arrivingCityPhotoUrl;

    private double price;


}
