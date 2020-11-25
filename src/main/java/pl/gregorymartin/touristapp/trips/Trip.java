package pl.gregorymartin.touristapp.trips;

import pl.gregorymartin.touristapp.users.Group;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name = "trips")
class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long fromCityId;
    private ZonedDateTime arriving;

    private long toCityId;
    private ZonedDateTime departure;

    private int size;
    private Double price;

    @OneToMany
    private Set<Group> groups;

}
