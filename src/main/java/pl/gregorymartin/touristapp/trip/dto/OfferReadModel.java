package pl.gregorymartin.touristapp.trip.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
public
class OfferReadModel {
    private long id;

    private String departureCityName;
    private ZonedDateTime departureTime;
    private String arrivingCityName;
    private ZonedDateTime arrivingTime;

    private int capacity;
    private double price;

    private List<BookingReadModel> bookings;
}
