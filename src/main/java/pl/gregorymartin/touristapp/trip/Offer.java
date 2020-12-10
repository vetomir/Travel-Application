package pl.gregorymartin.touristapp.trip;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private String arrivingCityPhotoUrl;
    private ZonedDateTime arrivingTime;

    private int capacity;
    private double price;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_id",  insertable = false)
    Set<Booking> bookings = new HashSet<>();


    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "offer_id",  insertable = false)
    private List<Comment> comments = new ArrayList<>();

    public Offer() {
    }

    Offer(final long id) {
        this.id = id;
    }
}
