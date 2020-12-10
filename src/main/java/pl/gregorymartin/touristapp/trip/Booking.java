package pl.gregorymartin.touristapp.trip;

import lombok.Getter;
import lombok.Setter;
import pl.gregorymartin.touristapp.user.AppUser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bookings")
@Getter
@Setter
public
class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "offer_id", updatable = false)
    private Offer offer;

    private int size;
    private double price;

    private boolean paid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id", updatable = false)
    private AppUser appUser;


    public Booking() {
        paid = false;
    }
}
