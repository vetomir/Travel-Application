package pl.gregorymartin.touristapp.places;

import javax.persistence.*;

@Entity
@Table(name = "cities")
class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

}
