package pl.gregorymartin.touristapp.trip;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "offers")
class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String departureCityName;
    private ZonedDateTime departureTime;
    private String arrivingCityName;
    private ZonedDateTime arrivingTime;
    private int capacity;
    private double price;

    @OneToMany
    Set<Booking> bookings;

    Offer() {
    }

    Offer(final long id) {
        this.id = id;
    }
}
