package pl.gregorymartin.touristapp.trip.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public
class OfferWriteModel {
    private String departureCityName;
    private ZonedDateTime departureTime;
    private String arrivingCityName;
    private ZonedDateTime arrivingTime;

    private int capacity;
    private double price;


    public OfferWriteModel(final String departureCityName, final ZonedDateTime departureTime, final String arrivingCityName, final ZonedDateTime arrivingTime, final int capacity, final double price) {
        this.departureCityName = departureCityName;
        this.departureTime = departureTime;
        this.arrivingCityName = arrivingCityName;
        this.arrivingTime = arrivingTime;
        this.capacity = capacity;
        this.price = price;
    }
}
