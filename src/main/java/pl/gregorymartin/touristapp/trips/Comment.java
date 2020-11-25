package pl.gregorymartin.touristapp.trips;

import pl.gregorymartin.touristapp.users.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "comments")
class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    @OneToMany
    private Set<Trip> trip;

    @ManyToOne
    private User user;
}
