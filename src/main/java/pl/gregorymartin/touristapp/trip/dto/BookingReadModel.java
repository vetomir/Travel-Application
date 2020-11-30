package pl.gregorymartin.touristapp.trip.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Builder
@Getter
@Setter
public
class BookingReadModel {
    private long id;

    private String departureCityName;
    private ZonedDateTime departureTime;
    private String arrivingCityName;
    private ZonedDateTime arrivingTime;

    private int amount;
    private double price;

    private String username;
}
