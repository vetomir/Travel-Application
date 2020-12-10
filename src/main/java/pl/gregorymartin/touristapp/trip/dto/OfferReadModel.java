package pl.gregorymartin.touristapp.trip.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    private CommentReadModel review;


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
