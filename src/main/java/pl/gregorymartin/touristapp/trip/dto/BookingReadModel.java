package pl.gregorymartin.touristapp.trip.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.gregorymartin.touristapp.user.dto.UserReadModel;

import java.time.ZonedDateTime;

@Builder
@Getter
@Setter
public
class BookingReadModel {
    private long id;

    private long offerId;

    private String departureCityName;
    private ZonedDateTime departureTime;
    private String arrivingCityName;
    private ZonedDateTime arrivingTime;

    private int size;
    private double price;

    private boolean paid;

    private UserReadModel appUser;
}
