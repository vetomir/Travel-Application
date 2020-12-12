package pl.gregorymartin.touristapp.trip.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@Getter
@Setter
public
class BookingUserPanel {
    private long id;

    private long offerId;

    private String departureCityName;
    private ZonedDateTime departureTime;
    private String arrivingCityName;
    private ZonedDateTime arrivingTime;

    private int size;
    private double price;

    private boolean paid;

    private boolean commented;

    private String photoUrl;

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
