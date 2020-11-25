package pl.gregorymartin.touristapp.places;

import javax.persistence.*;

@Entity
@Table(name = "countries")
class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
