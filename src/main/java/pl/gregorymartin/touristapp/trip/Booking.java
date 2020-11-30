package pl.gregorymartin.touristapp.trip;

import lombok.Getter;
import lombok.Setter;
import pl.gregorymartin.touristapp.user.AppUser;

import javax.persistence.*;

@Entity
@Table(name = "bookings")
@Getter
@Setter
public
class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", updatable = false)
    private Offer offer;

    private int amount;
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id", updatable = false)
    private AppUser appUser;

}
