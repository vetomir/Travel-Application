package pl.gregorymartin.touristapp.trip.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@Builder
public
class OfferSearch {
    private long id;
    private String departureCityName;
    private ZonedDateTime departureTime;
    private ZonedDateTime arrivingTime;
    private String arrivingCityName;
    private String arrivingCityPhotoUrl;
    private int days;

    private double price;

    public String departureTimeDate(){
        return departureTime.format(DateTimeFormatter.ofPattern("dd LLLL yyyy")).toUpperCase();
    }
    public String departureTimeHour(){
        return departureTime.format(DateTimeFormatter.ofPattern("hh:mm a")).toUpperCase();
    }
    public String arrivingTimeDate(){
        return arrivingTime.format(DateTimeFormatter.ofPattern("dd LLLL yyyy")).toUpperCase();
    }
    public String arrivingTimeHour(){
        return arrivingTime.format(DateTimeFormatter.ofPattern("hh:mm a")).toUpperCase();
    }
}
